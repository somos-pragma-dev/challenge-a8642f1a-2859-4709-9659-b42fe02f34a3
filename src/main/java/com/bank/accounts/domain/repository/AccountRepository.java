package com.bank.accounts.domain.repository;

import com.bank.accounts.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(String customerId);
    List<Account> findActiveByCustomerId(String customerId);
    List<Account> findByActive(boolean active);
    List<Account> findAll();
    Account save(Account account);
    Account update(Account account);
    void deleteById(UUID id);
    boolean existsByAccountNumber(String accountNumber);
    boolean existsById(UUID id);
    int updateBalance(UUID accountId, BigDecimal newBalance);
    boolean blockAccount(UUID accountId);
    boolean unblockAccount(UUID accountId);
    long count();
    List<Account> findByAccountType(String accountType);
}