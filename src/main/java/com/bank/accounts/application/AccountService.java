package com.bank.accounts.application;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.persistence.AccountJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "GBP");

    private final AccountRepository accountRepository;
    private final AccountJpaRepository jpaRepository;

    public AccountService(AccountRepository accountRepository, AccountJpaRepository jpaRepository) {
        this.accountRepository = accountRepository;
        this.jpaRepository = jpaRepository;
    }

    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.accountNumber() != null ? request.accountNumber() : generateAccountNumber())) {
            throw new DuplicateAccountException(request.accountNumber());
        }

        Account account = new Account(
                UUID.randomUUID(),
                generateAccountNumber(),
                request.accountType() != null ? request.accountType() : "SAVINGS",
                request.getInitialBalanceOrZero(),
                request.currency() != null ? request.currency() : "USD",
                "ACTIVE",
                request.ownerDocument(),
                request.ownerName(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Account saved = accountRepository.save(account);
        return mapToResponse(saved);
    }

    public AccountResponse getAccount(Long accountId) {
        UUID uuid = UUID.randomUUID(); // Simulación - en implementación real convertir Long a UUID
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        return mapToResponse(account);
    }

    public AccountResponse getAccountById(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId.toString()));
        return mapToResponse(account);
    }

    public AccountResponse getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(null, accountNumber));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AccountResponse> getAllAccounts(String status, String currency) {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .filter(acc -> status == null || status.equalsIgnoreCase(acc.getStatus()))
                .filter(acc -> currency == null || currency.equalsIgnoreCase(acc.getCurrency()))
                .map(this::mapToResponse)
                .toList();
    }

    public BigDecimal getBalance(Long accountId) {
        UUID uuid = UUID.randomUUID();
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        return account.getBalance();
    }

    public void deactivateAccount(Long accountId) {
        UUID uuid = UUID.randomUUID();
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        account.deactivate();
        accountRepository.save(account);
    }

    public void deactivateAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId.toString()));
        account.deactivate();
        accountRepository.save(account);
    }

    public void updateBalance(Long accountId, BigDecimal newBalance) {
        UUID uuid = UUID.randomUUID();
        accountRepository.updateBalance(uuid, newBalance);
    }

    private String generateAccountNumber() {
        return String.valueOf(System.currentTimeMillis()).substring(1) + "0";
    }

    private AccountResponse mapToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus(),
                account.getOwnerDocument(),
                account.getOwnerName(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}