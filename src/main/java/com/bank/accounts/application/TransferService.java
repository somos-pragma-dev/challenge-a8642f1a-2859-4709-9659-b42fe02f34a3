package com.bank.accounts.application;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import com.bank.accounts.infrastructure.idempotency.IdempotencyKeyGenerator;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    private final AccountRepository accountRepository;
    private final IdempotencyRepository idempotencyRepository;
    private final IdempotencyKeyGenerator idempotencyKeyGenerator;
    private final AuditEventPublisher auditEventPublisher;
    private final AccountService accountService;

    public TransferService(
            AccountRepository accountRepository,
            IdempotencyRepository idempotencyRepository,
            IdempotencyKeyGenerator idempotencyKeyGenerator,
            AuditEventPublisher auditEventPublisher,
            AccountService accountService) {
        this.accountRepository = accountRepository;
        this.idempotencyRepository = idempotencyRepository;
        this.idempotencyKeyGenerator = idempotencyKeyGenerator;
        this.auditEventPublisher = auditEventPublisher;
        this.accountService = accountService;
    }

    public TransferResponse executeTransfer(TransferRequest request, String channel) {
        if (request.isSameAccount()) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!request.isValidAmount()) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        String idempotencyKey = request.hasExternalReference()
                ? request.generateOperationKey()
                : idempotencyKeyGenerator.generateKey(
                        String.valueOf(System.currentTimeMillis()), channel);

        var existingRecord = idempotencyRepository.findByOperationKey(idempotencyKey);
        if (existingRecord.isPresent()) {
            logger.info("Idempotent request detected: {}", idempotencyKey);
            return new TransferResponse(
                    UUID.randomUUID(),
                    idempotencyKey,
                    "COMPLETED",
                    request.amount(),
                    BigDecimal.ZERO,
                    LocalDateTime.now().minusHours(1),
                    LocalDateTime.now(),
                    null
            );
        }

        UUID sourceAccountId = UUID.randomUUID();
        UUID targetAccountId = UUID.randomUUID();

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new AccountNotFoundException(sourceAccountId.toString()));

        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new AccountNotFoundException(targetAccountId.toString()));

        if (!sourceAccount.hasSufficientBalance(request.getAmountWithFee())) {
            throw new InsufficientBalanceException(
                    sourceAccount.getId().toString(),
                    sourceAccount.getAccountNumber(),
                    sourceAccount.getBalance(),
                    request.getAmountWithFee()
            );
        }

        sourceAccount.debit(request.getAmountWithFee());
        targetAccount.credit(request.amount());

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, request.amount(), channel);
        transfer.setIdempotencyKey(idempotencyKey);
        transfer.markAsCompleted();

        idempotencyRepository.save(new IdempotencyRepository.IdempotencyRecord(
                idempotencyKey, channel, "COMPLETED", LocalDateTime.now(), LocalDateTime.now().plusDays(30)
        ));

        auditEventPublisher.publishTransferExecuted(transfer, sourceAccount, targetAccount);

        return mapToResponse(transfer);
    }

    public Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, Optional<String> idempotencyKey) {
        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        String key = idempotencyKey.orElseGet(() ->
                IdempotencyKeyGenerator.generateIdempotencyKey("DEFAULT", String.valueOf(System.currentTimeMillis())));

        var existingRecord = idempotencyRepository.findByKey(key);
        if (existingRecord.isPresent()) {
            logger.info("Idempotent request detected: {}", key);
            return existingRecord.get().responseBody() != null ?
                    new Transfer(sourceAccountId, targetAccountId, amount, "DEFAULT") : null;
        }

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new AccountNotFoundException(sourceAccountId.toString()));

        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new AccountNotFoundException(targetAccountId.toString()));

        if (!sourceAccount.isActive()) {
            throw new IllegalStateException("Source account is not active");
        }

        if (!sourceAccount.hasSufficientBalance(amount)) {
            throw new InsufficientBalanceException(
                    sourceAccount.getId().toString(),
                    sourceAccount.getAccountNumber(),
                    sourceAccount.getBalance(),
                    amount
            );
        }

        sourceAccount.debit(amount);
        targetAccount.credit(amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, amount, "DEFAULT");
        transfer.setIdempotencyKey(key);
        transfer.markAsCompleted();

        idempotencyRepository.save(new IdempotencyRepository.IdempotencyRecord(
                key, "DEFAULT", "COMPLETED", LocalDateTime.now(), LocalDateTime.now().plusDays(30)
        ));

        return transfer;
    }

    private TransferResponse mapToResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getIdempotencyKey(),
                transfer.getStatus(),
                transfer.getAmount(),
                transfer.getFee(),
                transfer.getCreatedAt(),
                transfer.getCompletedAt(),
                transfer.getFailureReason()
        );
    }
}