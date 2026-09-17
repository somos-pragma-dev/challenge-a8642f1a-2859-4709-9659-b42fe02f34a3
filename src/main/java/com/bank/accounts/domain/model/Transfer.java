package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status;
    private String channel;
    private String idempotencyKey;
    private String operationNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime failedAt;
    private String failureReason;
    private int retryCount;

    private static final int MAX_RETRY_COUNT = 3;

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String channel) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.fee = calculateFee(amount);
        this.status = "PENDING";
        this.channel = channel;
        this.operationNumber = generateOperationNumber();
        this.createdAt = LocalDateTime.now();
        this.retryCount = 0;
    }

    private BigDecimal calculateFee(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("1000")) > 0) {
            return amount.multiply(new BigDecimal("0.01"));
        }
        return BigDecimal.ZERO;
    }

    private String generateOperationNumber() {
        return "OP" + System.currentTimeMillis();
    }

    public static String generateIdempotencyKey(String channel, String operationNumber) {
        return channel + "-" + operationNumber + "-" + System.currentTimeMillis();
    }

    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isProcessing() {
        return "PROCESSING".equalsIgnoreCase(status);
    }

    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }

    public void markAsProcessing() {
        this.status = "PROCESSING";
    }

    public void markAsCompleted() {
        this.status = "COMPLETED";
        this.completedAt = LocalDateTime.now();
    }

    public void markAsFailed(String reason) {
        this.status = "FAILED";
        this.failedAt = LocalDateTime.now();
        this.failureReason = reason;
    }

    public void markAsRejected(String reason) {
        this.status = "REJECTED";
        this.failureReason = reason;
    }

    public boolean canRetry() {
        return retryCount < MAX_RETRY_COUNT;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public boolean isIdempotent(String newIdempotencyKey) {
        return this.idempotencyKey != null && this.idempotencyKey.equals(newIdempotencyKey);
    }

    public BigDecimal getTotalAmount() {
        return amount.add(fee != null ? fee : BigDecimal.ZERO);
    }

    public boolean isSameAccount() {
        return sourceAccountId.equals(targetAccountId);
    }

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isInterbank() {
        return false;
    }

    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("5000")) >= 0;
    }

    public long getProcessingTimeSeconds() {
        if (completedAt == null || createdAt == null) return 0;
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public String getStatusDescription() {
        return status != null ? status : "UNKNOWN";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(UUID sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(UUID targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public UUID getOriginAccountId() {
        return sourceAccountId;
    }

    public UUID getDestinationAccountId() {
        return targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}