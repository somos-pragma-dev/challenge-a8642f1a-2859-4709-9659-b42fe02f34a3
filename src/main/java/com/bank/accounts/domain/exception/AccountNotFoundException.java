package com.bank.accounts.domain.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String accountId;
    private final String accountNumber;
    private final LocalDateTime occurredAt;
    private final String errorCode;
    private final Map<String, Object> context;
    
    public AccountNotFoundException(String accountId) {
        super(buildDefaultMessage(accountId, null));
        this.accountId = accountId;
        this.accountNumber = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, String accountNumber) {
        super(buildDefaultMessage(accountId, accountNumber));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, String accountNumber, String customMessage) {
        super(customMessage);
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, Throwable cause) {
        super(buildDefaultMessage(accountId, null), cause);
        this.accountId = accountId;
        this.accountNumber = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    private static String buildDefaultMessage(String accountId, String accountNumber) {
        if (accountNumber != null && !accountNumber.isEmpty()) {
            return String.format("Cuenta no encontrada: ID=%s, Número de cuenta=%s", accountId, accountNumber);
        }
        return String.format("Cuenta no encontrada con ID: %s", accountId);
    }
    
    private void initializeContext() {
        this.context.put("accountId", this.accountId);
        this.context.put("accountNumber", this.accountNumber);
        this.context.put("occurredAt", this.occurredAt.format(FORMATTER));
        this.context.put("errorCode", this.errorCode);
        this.context.put("traceId", UUID.randomUUID().toString());
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
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
    
    public String getFormattedOccurredAt() {
        return occurredAt.format(FORMATTER);
    }
    
    public boolean hasAccountNumber() {
        return accountNumber != null && !accountNumber.isEmpty();
    }
    
    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccountNotFoundException{\n");
        sb.append("  errorCode: ").append(errorCode).append("\n");
        sb.append("  accountId: ").append(accountId).append("\n");
        if (hasAccountNumber()) {
            sb.append("  accountNumber: ").append(accountNumber).append("\n");
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