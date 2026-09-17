package com.bank.accounts.application;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private AccountRequest createAccountRequest;
    private UUID accountId;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        testAccount = Account.builder()
                .id(accountId)
                .accountNumber("9876543210")
                .balance(new BigDecimal("2500.00"))
                .currency("USD")
                .active(true)
                .createdAt(Instant.now())
                .build();

        createAccountRequest = new AccountRequest(new BigDecimal("1000.00"), "USD");
    }

    @Test
    @DisplayName("Crear cuenta - éxito")
    void createAccount_Success() {
        when(accountRepository.existsByAccountNumber(anyString())).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        AccountResponse result = accountService.createAccount(createAccountRequest);

        assertNotNull(result);
        assertEquals(accountId, result.id());
        assertEquals("9876543210", result.accountNumber());
        assertEquals(new BigDecimal("2500.00"), result.balance());
        assertEquals("USD", result.currency());
        assertTrue(result.isActive());
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("Crear cuenta - número duplicado")
    void createAccount_DuplicateNumber() {
        when(accountRepository.existsByAccountNumber("1234567890")).thenReturn(true);

        assertThrows(DuplicateAccountException.class, () -> {
            accountService.createAccount(new AccountRequest(new BigDecimal("500.00"), "USD"));
        });

        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Obtener cuenta por ID - éxito")
    void getAccountById_Success() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

        AccountResponse result = accountService.getAccountById(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.id());
        assertEquals("9876543210", result.accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por ID - no encontrada")
    void getAccountById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(accountRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountById(randomId);
        });
    }

    @Test
    @DisplayName("Obtener todas las cuentas")
    void getAllAccounts_Success() {
        when(accountRepository.findAll()).thenReturn(List.of(testAccount));

        List<AccountResponse> result = accountService.getAllAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("9876543210", result.get(0).accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por número - éxito")
    void getAccountByNumber_Success() {
        when(accountRepository.findByAccountNumber("9876543210"))
                .thenReturn(Optional.of(testAccount));

        AccountResponse result = accountService.getAccountByNumber("9876543210");

        assertNotNull(result);
        assertEquals("9876543210", result.accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por número - no encontrada")
    void getAccountByNumber_NotFound() {
        when(accountRepository.findByAccountNumber("0000000000")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountByNumber("0000000000");
        });
    }

    @Test
    @DisplayName("Desactivar cuenta - éxito")
    void deactivateAccount_Success() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        accountService.deactivateAccount(accountId);

        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("Desactivar cuenta - no encontrada")
    void deactivateAccount_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(accountRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deactivateAccount(randomId);
        });
    }
}