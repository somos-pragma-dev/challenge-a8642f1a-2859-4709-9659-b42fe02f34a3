package com.bank.accounts.domain.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DuplicateAccountException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String accountNumber;
    private final String accountHolderId;
    private final LocalDateTime occurredAt;
    private final String errorCode;
    private final Map<String, Object> context;
    private final LocalDateTime existingAccountCreatedAt;
    
    public DuplicateAccountException(String accountNumber) {
        super(buildDefaultMessage(accountNumber, null));
        this.accountNumber = accountNumber;
        this.accountHolderId = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId) {
        super(buildDefaultMessage(accountNumber, accountHolderId));
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId, LocalDateTime existingAccountCreatedAt) {
        super(buildDetailedMessage(accountNumber, accountHolderId, existingAccountCreatedAt));
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = existingAccountCreatedAt;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId, String customMessage) {
        super(customMessage);
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, Throwable cause) {
        super(buildDefaultMessage(accountNumber, null), cause);
        this.accountNumber = accountNumber;
        this.accountHolderId = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    private static String buildDefaultMessage(String accountNumber, String accountHolderId) {
        if (accountHolderId != null && !accountHolderId.isEmpty()) {
            return String.format("Ya existe una cuenta con el número: %s para el titular: %s", accountNumber, accountHolderId);
        }
        return String.format("Ya existe una cuenta con el número: %s", accountNumber);
    }
    
    private static String buildDetailedMessage(String accountNumber, String accountHolderId, LocalDateTime existingCreatedAt) {
        String baseMessage = buildDefaultMessage(accountNumber, accountHolderId);
        if (existingCreatedAt != null) {
            return baseMessage + ". La cuenta existente fue creada el: " + existingCreatedAt.format(FORMATTER);
        }
        return baseMessage;
    }
    
    private void initializeContext() {
        this.context.put("accountNumber", this.accountNumber);
        this.context.put("accountHolderId", this.accountHolderId);
        this.context.put("occurredAt", this.occurredAt.format(FORMATTER));
        this.context.put("errorCode", this.errorCode);
        this.context.put("traceId", UUID.randomUUID().toString());
        if (existingAccountCreatedAt != null) {
            this.context.put("existingAccountCreatedAt", existingAccountCreatedAt.format(FORMATTER));
        }
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderId() {
        return accountHolderId;
    }
    
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public Map<String, Object> getContext() {
        return Map.copyOf(context);
    }
    
    public LocalDateTime getExistingAccountCreatedAt() {
        return existingAccountCreatedAt;
    }
    
    public String getFormattedOccurredAt() {
        return occurredAt.format(FORMATTER);
    }
    
    public boolean hasAccountHolderId() {
        return accountHolderId != null && !accountHolderId.isEmpty();
    }
    
    public boolean hasExistingAccountInfo() {
        return existingAccountCreatedAt != null;
    }
    
    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("DuplicateAccountException{\n");
        sb.append("  errorCode: ").append(errorCode).append("\n");
        sb.append("  accountNumber: ").append(accountNumber).append("\n");
        if (hasAccountHolderId()) {
            sb.append("  accountHolderId: ").append(accountHolderId).append("\n");
        }
        if (hasExistingAccountInfo()) {
            sb.append("  existingAccountCreatedAt: ").append(existingAccountCreatedAt.format(FORMATTER)).append("\n");
        }
        sb.append("  occurredAt: ").append(getFormattedOccurredAt()).append("\n");
        sb.append("  traceId: ").append(context.get("traceId")).append("\n");
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getDetailedMessage();
    }
}