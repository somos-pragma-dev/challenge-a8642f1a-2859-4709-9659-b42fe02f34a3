package com.bank.accounts.api;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.application.TransferService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransferService transferService;

    private TransferRequest transferRequest;
    private TransferResponse transferResponse;
    private UUID fromAccountId;
    private UUID toAccountId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        fromAccountId = UUID.randomUUID();
        toAccountId = UUID.randomUUID();
        idempotencyKey = "OP-20240115-001-CHANNEL_A";

        transferRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        transferResponse = new TransferResponse(
                UUID.randomUUID(),
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                new BigDecimal("2.50"),
                "COMPLETED",
                Instant.now(),
                idempotencyKey
        );
    }

    @Test
    @DisplayName("Realizar transferencia - éxito")
    void transfer_Success() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fromAccountId").value(fromAccountId.toString()))
                .andExpect(jsonPath("$.toAccountId").value(toAccountId.toString()))
                .andExpect(jsonPath("$.amount").value(100.00))
                .andExpect(jsonPath("$.fee").value(2.50))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Realizar transferencia - idempotencia con clave repetida")
    void transfer_IdempotencyKeyReused() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        verify(transferService, times(2)).executeTransfer(any(TransferRequest.class), any(String.class));
    }

    @Test
    @DisplayName("Realizar transferencia - cuenta origen no encontrada")
    void transfer_FromAccountNotFound() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenThrow(new AccountNotFoundException("Source account not found"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - saldo insuficiente")
    void transfer_InsufficientBalance() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenThrow(new InsufficientBalanceException("Insufficient balance"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - misma cuenta origen y destino")
    void transfer_SameAccount() throws Exception {
        TransferRequest sameAccountRequest = new TransferRequest(
                fromAccountId,
                fromAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(sameAccountRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Realizar transferencia - monto inválido")
    void transfer_InvalidAmount() throws Exception {
        TransferRequest invalidAmountRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("-50.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(invalidAmountRequest)))
                .andExpect(status().isBadRequest());
    }
}