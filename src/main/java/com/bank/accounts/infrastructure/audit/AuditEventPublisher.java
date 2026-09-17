package com.bank.accounts.infrastructure.audit;

import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(AuditEventPublisher.class);

    public void publishTransferExecuted(Transfer transfer, Account sourceAccount, Account targetAccount) {
        logger.info("Publicando evento de transferencia ejecutada: transferId={}, source={}, target={}, amount={}",
                transfer.getId(), transfer.getSourceAccountNumber(), 
                transfer.getTargetAccountNumber(), transfer.getAmount());

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("TRANSFER_EXECUTED")
                .timestamp(LocalDateTime.now())
                .payload(buildTransferPayload(transfer, sourceAccount, targetAccount))
                .build();

        persistAuditEvent(event);
        notifyExternalSystems(event);
    }

    public void publishTransferFailed(Transfer transfer, String failureReason) {
        logger.warn("Publicando evento de transferencia fallida: transferId={}, reason={}",
                transfer.getId(), failureReason);

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("TRANSFER_FAILED")
                .timestamp(LocalDateTime.now())
                .payload(buildFailurePayload(transfer, failureReason))
                .build();

        persistAuditEvent(event);
    }

    public void publishIdempotencyCheck(String idempotencyKey, boolean wasDuplicated) {
        logger.debug("Verificación de idempotencia: key={}, duplicated={}", idempotencyKey, wasDuplicated);

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("IDEMPOTENCY_CHECK")
                .timestamp(LocalDateTime.now())
                .payload(buildIdempotencyPayload(idempotencyKey, wasDuplicated))
                .build();

        persistAuditEvent(event);
    }

    private Map<String, Object> buildTransferPayload(Transfer transfer, 
            Account sourceAccount, Account targetAccount) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transferId", transfer.getId());
        payload.put("sourceAccountNumber", transfer.getSourceAccountNumber());
        payload.put("sourceAccountType", sourceAccount.getAccountType());
        payload.put("targetAccountNumber", transfer.getTargetAccountNumber());
        payload.put("targetAccountType", targetAccount.getAccountType());
        payload.put("amount", transfer.getAmount().toString());
        payload.put("channel", transfer.getChannel());
        payload.put("externalReference", transfer.getExternalReference());
        payload.put("sourceBalanceAfter", sourceAccount.getBalance().toString());
        payload.put("targetBalanceAfter", targetAccount.getBalance().toString());
        return payload;
    }

    private Map<String, Object> buildFailurePayload(Transfer transfer, String failureReason) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transferId", transfer.getId());
        payload.put("sourceAccountNumber", transfer.getSourceAccountNumber());
        payload.put("targetAccountNumber", transfer.getTargetAccountNumber());
        payload.put("amount", transfer.getAmount().toString());
        payload.put("failureReason", failureReason);
        payload.put("channel", transfer.getChannel());
        return payload;
    }

    private Map<String, Object> buildIdempotencyPayload(String idempotencyKey, boolean wasDuplicated) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("idempotencyKey", idempotencyKey);
        payload.put("wasDuplicated", wasDuplicated);
        return payload;
    }

    private void persistAuditEvent(AuditEvent event) {
        logger.debug("Persisting audit event: type={}, eventId={}", 
                event.getEventType(), event.getEventId());
    }

    private void notifyExternalSystems(AuditEvent event) {
        logger.debug("Notificando sistemas externos sobre evento: type={}", event.getEventType());
    }

    public static class AuditEvent {
        private final String eventId;
        private final String eventType;
        private final LocalDateTime timestamp;
        private final Map<String, Object> payload;

        private AuditEvent(Builder builder) {
            this.eventId = builder.eventId;
            this.eventType = builder.eventType;
            this.timestamp = builder.timestamp;
            this.payload = builder.payload;
        }

        public static Builder builder() {
            return new Builder();
        }

        public String getEventId() {
            return eventId;
        }

        public String getEventType() {
            return eventType;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public Map<String, Object> getPayload() {
            return payload;
        }

        public static class Builder {
            private String eventId;
            private String eventType;
            private LocalDateTime timestamp;
            private Map<String, Object> payload;

            public Builder eventId(String eventId) {
                this.eventId = eventId;
                return this;
            }

            public Builder eventType(String eventType) {
                this.eventType = eventType;
                return this;
            }

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder payload(Map<String, Object> payload) {
                this.payload = payload;
                return this;
            }

            public AuditEvent build() {
                return new AuditEvent(this);
            }
        }
    }
}