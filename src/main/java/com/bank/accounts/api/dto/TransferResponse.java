package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponse(
    UUID id,
    String operationKey,
    String status,
    BigDecimal amount,
    BigDecimal fee,
    LocalDateTime createdAt,
    LocalDateTime completedAt,
    String failureReason
) {
    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }

    public boolean hasFee() {
        return fee != null && fee.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getNetAmount() {
        return amount;
    }

    public String getFormattedAmount() {
        return String.format("%.2f", amount);
    }

    public String getFormattedFee() {
        return fee != null ? String.format("%.2f", fee) : "0.00";
    }

    public String getFormattedTotalAmount() {
        BigDecimal total = amount.add(fee != null ? fee : BigDecimal.ZERO);
        return String.format("%.2f", total);
    }

    public String getStatusDisplayName() {
        return status != null ? status : "UNKNOWN";
    }

    public long getProcessingTimeSeconds() {
        if (completedAt == null || createdAt == null) return 0;
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public boolean wasProcessedQuickly() {
        return getProcessingTimeSeconds() < 5;
    }
}