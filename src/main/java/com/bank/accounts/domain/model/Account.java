package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Account {

    public enum AccountStatus {
        ACTIVE, INACTIVE, BLOCKED
    }

    private UUID id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;
    private String ownerDocument;
    private String ownerName;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastTransactionAt;
    private int dailyTransactionCount;
    private BigDecimal dailyTotalAmount;
    private String customerId;

    public Account() {
    }

    public Account(UUID id, String accountNumber, String accountType, BigDecimal balance,
                   String currency, AccountStatus status, String ownerDocument, String ownerName,
                   LocalDate createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.ownerDocument = ownerDocument;
        this.ownerName = ownerName;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor a cero");
        }
        this.balance = this.balance.add(amount);
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero");
        }
        if (!hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.balance = this.balance.subtract(amount);
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    public boolean isActive() {
        return this.status == AccountStatus.ACTIVE;
    }

    public boolean isInactive() {
        return this.status == AccountStatus.INACTIVE;
    }

    public boolean isBlocked() {
        return this.status == AccountStatus.BLOCKED;
    }

    public void activate() {
        this.status = AccountStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = AccountStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.status = AccountStatus.BLOCKED;
        this.updatedAt = LocalDateTime.now();
    }

    public void resetDailyLimits() {
        this.dailyTransactionCount = 0;
        this.dailyTotalAmount = BigDecimal.ZERO;
    }

    public boolean hasReachedDailyLimit(int maxTransactions) {
        return this.dailyTransactionCount >= maxTransactions;
    }

    public boolean hasReachedDailyAmountLimit(BigDecimal maxAmount) {
        return this.dailyTotalAmount.compareTo(maxAmount) >= 0;
    }

    public String getFormattedBalance() {
        return String.format("%s %s", this.currency, this.balance.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public String getAccountTypeDescription() {
        return switch (this.accountType) {
            case "SAVINGS" -> "Cuenta de Ahorros";
            case "CHECKING" -> "Cuenta Corriente";
            case "INVESTMENT" -> "Cuenta de Inversión";
            default -> "Cuenta " + this.accountType;
        };
    }

    public String getStatusDescription() {
        return switch (this.status) {
            case ACTIVE -> "Activa";
            case INACTIVE -> "Inactiva";
            case BLOCKED -> "Bloqueada";
        };
    }

    public long getDaysSinceCreation() {
        return this.createdAt != null ? ChronoUnit.DAYS.between(this.createdAt, LocalDate.now()) : 0;
    }

    public boolean isNewAccount() {
        return getDaysSinceCreation() < 30;
    }

    public boolean hasPositiveBalance() {
        return this.balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasZeroBalance() {
        return this.balance.compareTo(BigDecimal.ZERO) == 0;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public String getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(String ownerDocument) { this.ownerDocument = ownerDocument; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getLastTransactionAt() { return lastTransactionAt; }
    public void setLastTransactionAt(LocalDateTime lastTransactionAt) { this.lastTransactionAt = lastTransactionAt; }

    public int getDailyTransactionCount() { return dailyTransactionCount; }
    public void setDailyTransactionCount(int dailyTransactionCount) { this.dailyTransactionCount = dailyTransactionCount; }

    public BigDecimal getDailyTotalAmount() { return dailyTotalAmount; }
    public void setDailyTotalAmount(BigDecimal dailyTotalAmount) { this.dailyTotalAmount = dailyTotalAmount; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public LocalDateTime getLastModifiedAt() { return updatedAt; }
    public void setLastModifiedAt(LocalDateTime lastModifiedAt) { this.updatedAt = lastModifiedAt; }
}