package com.bank.accounts.api;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.application.AccountService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountService accountService;

    private Account testAccount;
    private AccountResponse testAccountResponse;
    private AccountRequest testAccountRequest;

    @BeforeEach
    void setUp() {
        testAccount = new Account(
                UUID.randomUUID(),
                "1234567890",
                "SAVINGS",
                new BigDecimal("1000.00"),
                "USD",
                "ACTIVE",
                "DOC123",
                "Test Owner",
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                0,
                BigDecimal.ZERO
        );

        testAccountResponse = new AccountResponse(
                testAccount.getId(),
                testAccount.getAccountNumber(),
                testAccount.getBalance(),
                testAccount.getCurrency(),
                testAccount.isActive(),
                testAccount.getCreatedAt()
        );

        testAccountRequest = new AccountRequest(new BigDecimal("500.00"), "USD");
    }

    @Test
    @DisplayName("Crear cuenta - éxito")
    void createAccount_Success() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(testAccountResponse);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testAccount.getId().toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.balance").value(1000.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @DisplayName("Crear cuenta - número duplicado")
    void createAccount_DuplicateNumber() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenThrow(new DuplicateAccountException("Account number already exists"));

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Consultar cuenta por ID - éxito")
    void getAccountById_Success() throws Exception {
        Long accountId = 1L;
        when(accountService.getAccount(accountId)).thenReturn(testAccountResponse);

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testAccount.getId().toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
    }

    @Test
    @DisplayName("Consultar cuenta por ID - no encontrada")
    void getAccountById_NotFound() throws Exception {
        Long accountId = 999L;
        when(accountService.getAccount(accountId))
                .thenThrow(new AccountNotFoundException("Account not found"));

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Listar todas las cuentas")
    void getAllAccounts_Success() throws Exception {
        when(accountService.getAllAccounts(eq((String) null), eq((String) null)))
                .thenReturn(List.of(testAccountResponse));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"));
    }
}