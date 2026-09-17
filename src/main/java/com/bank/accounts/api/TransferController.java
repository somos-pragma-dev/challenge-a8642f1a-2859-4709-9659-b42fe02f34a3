package com.bank.accounts.api;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.application.TransferService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import com.bank.accounts.infrastructure.idempotency.IdempotencyKeyGenerator;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;
    private final IdempotencyRepository idempotencyRepository;
    private final IdempotencyKeyGenerator keyGenerator;
    private final AuditEventPublisher auditPublisher;

    public TransferController(
            TransferService transferService,
            IdempotencyRepository idempotencyRepository,
            IdempotencyKeyGenerator keyGenerator,
            AuditEventPublisher auditPublisher) {
        this.transferService = transferService;
        this.idempotencyRepository = idempotencyRepository;
        this.keyGenerator = keyGenerator;
        this.auditPublisher = auditPublisher;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> executeTransfer(
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody TransferRequest request) {
        
        logger.info("Recibida solicitud de transferencia - origen: {}, destino: {}, monto: {}",
                request.originAccountId(), request.destinationAccountId(), request.getAmountWithFee());

        if (!request.isValidAmount()) {
            logger.warn("Monto de transferencia invalido");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto de transferencia debe ser mayor a cero");
        }

        if (request.isSameAccount()) {
            logger.warn("Intento de transferencia a la misma cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede transferir a la misma cuenta");
        }

        String operationKey = (idempotencyKey != null && !idempotencyKey.isBlank())
                ? keyGenerator.generateKey(idempotencyKey, "TRANSFER")
                : request.generateOperationKey();

        if (idempotencyRepository.existsByOperationKey(operationKey)) {
            logger.info("Operacion idempotente detectada - clave: {}", operationKey);
            TransferResponse cachedResponse = idempotencyRepository.findByOperationKey(operationKey);
            if (cachedResponse != null) {
                return ResponseEntity.ok(cachedResponse);
            }
        }

        try {
            TransferResponse response = transferService.executeTransfer(request, operationKey);
            idempotencyRepository.save(operationKey, response, "TRANSFER");
            
            auditPublisher.publishTransferExecuted(
                    response.originAccountId(),
                    response.destinationAccountId(),
                    response.amount(),
                    operationKey);
            
            logger.info("Transferencia ejecutada exitosamente - ID: {}, clave idempotencia: {}",
                    response.transferId(), operationKey);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (AccountNotFoundException e) {
            logger.error("Cuenta no encontrada en transferencia: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InsufficientBalanceException e) {
            logger.warn("Saldo insuficiente para transferencia: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error al ejecutar transferencia: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la transferencia");
        }
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<TransferResponse> getTransfer(@PathVariable Long transferId) {
        logger.info("Consultando transferencia con ID: {}", transferId);
        
        try {
            TransferResponse response = transferService.getTransfer(transferId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.warn("Transferencia no encontrada: {}", transferId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrada");
        }
    }

    @GetMapping("/by-operation-key/{operationKey}")
    public ResponseEntity<TransferResponse> getTransferByOperationKey(@PathVariable String operationKey) {
        logger.info("Consultando transferencia por clave de operacion: {}", operationKey);
        
        TransferResponse response = idempotencyRepository.findByOperationKey(operationKey);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe transferencia con esta clave de operacion");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getTransfers(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Instant fromDate,
            @RequestParam(required = false) Instant toDate) {
        
        logger.info("Listando transferencias - cuenta: {}, status: {}, rango: {} - {}",
                accountId, status, fromDate, toDate);
        
        if (accountId != null) {
            return ResponseEntity.ok(transferService.getTransfersByAccount(accountId));
        }
        
        return ResponseEntity.ok(transferService.getAllTransfers());
    }
}