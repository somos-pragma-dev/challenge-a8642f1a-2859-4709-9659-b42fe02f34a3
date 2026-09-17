package com.bank.accounts.api;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.application.AccountService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AuditEventPublisher auditPublisher;

    public AccountController(AccountService accountService, AuditEventPublisher auditPublisher) {
        this.accountService = accountService;
        this.auditPublisher = auditPublisher;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        logger.info("Recibida solicitud de creacion de cuenta");
        
        if (!request.isInitialBalanceValid()) {
            logger.warn("Balance inicial invalido: {}", request.getInitialBalanceOrZero());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El balance inicial no puede ser negativo");
        }
        
        if (!request.isCurrencySupported()) {
            logger.warn("Moneda no soportada en solicitud");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moneda no soportada");
        }

        try {
            AccountResponse response = accountService.createAccount(request);
            auditPublisher.publishAccountCreated(response.accountId(), response.accountNumber());
            logger.info("Cuenta creada exitosamente con ID: {}", response.accountId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateAccountException e) {
            logger.error("Duplicacion de cuenta detectada: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        logger.info("Consultando cuenta con ID: {}", accountId);
        
        if (accountId == null || accountId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de cuenta invalido");
        }

        try {
            AccountResponse response = accountService.getAccount(accountId);
            auditPublisher.publishAccountAccessed(accountId);
            return ResponseEntity.ok(response);
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String currency) {
        logger.info("Listando todas las cuentas - filtro status: {}, currency: {}", status, currency);
        
        List<AccountResponse> accounts = accountService.getAllAccounts(status, currency);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId) {
        logger.info("Consultando balance de cuenta: {}", accountId);
        
        try {
            BigDecimal balance = accountService.getBalance(accountId);
            return ResponseEntity.ok(balance);
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada para consulta de balance: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long accountId) {
        logger.info("Solicitud de desactivacion de cuenta: {}", accountId);
        
        try {
            accountService.deactivateAccount(accountId);
            auditPublisher.publishAccountDeactivated(accountId);
            return ResponseEntity.noContent().build();
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada para desactivacion: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}