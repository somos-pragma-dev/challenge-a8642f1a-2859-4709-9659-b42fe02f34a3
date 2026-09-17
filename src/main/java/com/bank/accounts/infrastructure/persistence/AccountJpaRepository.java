package com.bank.accounts.infrastructure.persistence;

import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountJpaRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> findById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", accountNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.ownerDocument = :customerId", Account.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Account> findActiveByCustomerId(String customerId) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.ownerDocument = :customerId AND a.status = 'ACTIVE'", Account.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Account> findByActive(boolean active) {
        String status = active ? "ACTIVE" : "INACTIVE";
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.status = :status", Account.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            entityManager.persist(account);
            return account;
        } else {
            return entityManager.merge(account);
        }
    }

    @Override
    public Account update(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void deleteById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber", Long.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return account != null;
    }

    @Override
    public int updateBalance(UUID accountId, BigDecimal newBalance) {
        return entityManager.createQuery(
                "UPDATE Account a SET a.balance = :newBalance, a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("newBalance", newBalance)
                .setParameter("id", accountId)
                .executeUpdate();
    }

    @Override
    public boolean blockAccount(UUID accountId) {
        int updated = entityManager.createQuery(
                "UPDATE Account a SET a.status = 'BLOCKED', a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("id", accountId)
                .executeUpdate();
        return updated > 0;
    }

    @Override
    public boolean unblockAccount(UUID accountId) {
        int updated = entityManager.createQuery(
                "UPDATE Account a SET a.status = 'ACTIVE', a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("id", accountId)
                .executeUpdate();
        return updated > 0;
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Account a", Long.class).getSingleResult();
    }

    @Override
    public List<Account> findByAccountType(String accountType) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.accountType = :accountType", Account.class)
                .setParameter("accountType", accountType)
                .getResultList();
    }
}