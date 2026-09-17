package com.bank.accounts.infrastructure.idempotency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IdempotencyRepository {
    boolean existsByOperationKey(String operationKey);
    void save(IdempotencyRecord record);
    Optional<IdempotencyRecord> findByOperationKey(String operationKey);
    Optional<IdempotencyRecord> findByKey(String key);
    void deleteByOperationKey(String operationKey);
    void deleteExpiredRecords(LocalDateTime cutoffDate);
    long countByChannelAndCreatedAfter(String channel, LocalDateTime after);
    List<IdempotencyRecord> findByChannelAndCreatedBetween(String channel, LocalDateTime start, LocalDateTime end);

    record IdempotencyRecord(
            String operationKey,
            String channel,
            String responseBody,
            LocalDateTime createdAt,
            LocalDateTime expiresAt
    ) {}
}