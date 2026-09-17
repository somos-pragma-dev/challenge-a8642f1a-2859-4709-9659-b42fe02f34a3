package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
    UUID id,
    String accountNumber,
    String accountType,
    BigDecimal balance,
    String currency,
    String status,
    String ownerDocument,
    String ownerName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    public boolean isInactive() {
        return "INACTIVE".equalsIgnoreCase(status);
    }

    public boolean isBlocked() {
        return "BLOCKED".equalsIgnoreCase(status);
    }

    public boolean hasPositiveBalance() {
        return balance != null && balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return balance != null && balance.compareTo(amount) >= 0;
    }

    public String getFormattedBalance() {
        if (balance == null) return "0.00";
        return String.format("%s %s", currency, balance.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public String getAccountTypeDescription() {
        return accountType != null ? accountType : "UNKNOWN";
    }

    public String getStatusDescription() {
        return status != null ? status : "UNKNOWN";
    }

    public long getDaysSinceCreation() {
        if (createdAt == null) return 0;
        return java.time.Duration.between(createdAt, LocalDateTime.now()).toDays();
    }
}