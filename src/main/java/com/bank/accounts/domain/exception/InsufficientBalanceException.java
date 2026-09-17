package com.bank.accounts.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {
    private final UUID accountId;
    private final String accountNumber;
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final BigDecimal shortfall;

    public InsufficientBalanceException(UUID accountId, String accountNumber,
            BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(buildMessage(accountNumber, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.shortfall = requestedAmount.subtract(currentBalance);
    }

    public InsufficientBalanceException(String message) {
        super(message);
        this.accountId = null;
        this.accountNumber = null;
        this.currentBalance = null;
        this.requestedAmount = null;
        this.shortfall = null;
    }

    private static String buildMessage(String accountNumber, BigDecimal current, BigDecimal requested) {
        return String.format("Insufficient balance in account %s. Current: %s, Requested: %s",
                accountNumber, current, requested);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public BigDecimal getShortfall() {
        return shortfall;
    }
}