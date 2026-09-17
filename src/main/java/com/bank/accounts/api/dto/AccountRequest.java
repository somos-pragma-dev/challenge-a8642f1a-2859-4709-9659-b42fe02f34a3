package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountRequest(
    String accountNumber,
    String accountType,
    String currency,
    BigDecimal initialBalance,
    String ownerDocument,
    String ownerName
) {
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "MXN", "COP");
    private static final BigDecimal MIN_INITIAL_BALANCE = BigDecimal.ZERO;
    private static final BigDecimal MAX_INITIAL_BALANCE = new BigDecimal("1000000");

    public BigDecimal getInitialBalanceOrZero() {
        return initialBalance != null ? initialBalance : BigDecimal.ZERO;
    }

    public boolean isInitialBalanceValid() {
        BigDecimal balance = getInitialBalanceOrZero();
        return balance.compareTo(MIN_INITIAL_BALANCE) >= 0 
            && balance.compareTo(MAX_INITIAL_BALANCE) <= 0;
    }

    public boolean isCurrencySupported() {
        return currency != null && SUPPORTED_CURRENCIES.contains(currency.toUpperCase());
    }
}