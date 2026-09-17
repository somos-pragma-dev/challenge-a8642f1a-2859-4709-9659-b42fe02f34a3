package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(
    UUID sourceAccountId,
    UUID targetAccountId,
    BigDecimal amount,
    String channel,
    String externalReference,
    String description
) {
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    private static final BigDecimal FEE_PERCENTAGE = new BigDecimal("0.005");
    private static final BigDecimal MAX_FEE = new BigDecimal("50.00");

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(MIN_AMOUNT) >= 0;
    }

    public boolean isSameAccount() {
        return sourceAccountId != null && sourceAccountId.equals(targetAccountId);
    }

    public boolean hasExternalReference() {
        return externalReference != null && !externalReference.isBlank();
    }

    public String generateOperationKey() {
        return sourceAccountId.toString() + "-" + targetAccountId.toString() + "-" + System.currentTimeMillis();
    }

    public BigDecimal getAmountWithFee() {
        return amount.add(calculateFee());
    }

    private BigDecimal calculateFee() {
        BigDecimal fee = amount.multiply(FEE_PERCENTAGE);
        return fee.compareTo(MAX_FEE) > 0 ? MAX_FEE : fee;
    }
}