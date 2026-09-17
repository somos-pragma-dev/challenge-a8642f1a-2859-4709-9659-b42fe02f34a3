package com.bank.accounts.application;

import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransferService - Pruebas Unitarias")
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IdempotencyRepository idempotencyRepository;

    @InjectMocks
    private TransferService transferService;

    private Account cuentaOrigen;
    private Account cuentaDestino;

    @BeforeEach
    void setUp() {
        cuentaOrigen = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("1234567890")
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        cuentaDestino = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("0987654321")
                .balance(new BigDecimal("500.00"))
                .currency("USD")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("Escenarios de Transferencia Exitosa")
    class TransferenciaExitosa {

        @Test
        @DisplayName("Debería ejecutar transferencia cuando las cuentas existen y tienen saldo suficiente")
        void deberiaEjecutarTransferencia_CuandoCuentasExisten_Y tieneSaldoSuficiente() {
            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("200.00"),
                    Optional.empty()
            );

            assertNotNull(resultado);
            assertEquals(cuentaOrigen.getId(), resultado.getOriginAccountId());
            assertEquals(cuentaDestino.getId(), resultado.getDestinationAccountId());
            assertEquals(new BigDecimal("200.00"), resultado.getAmount());

            verify(accountRepository, times(2)).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería actualizar saldos correctamente después de la transferencia")
        void deberiaActualizarSaldos_Correctamente() {
            BigDecimal montoTransferencia = new BigDecimal("300.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    montoTransferencia,
                    Optional.empty()
            );

            ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository, times(2)).save(captor.capture());

            Account cuentaOrigenActualizada = captor.getAllValues().get(0);
            Account cuentaDestinoActualizada = captor.getAllValues().get(1);

            assertEquals(new BigDecimal("700.00"), cuentaOrigenActualizada.getBalance());
            assertEquals(new BigDecimal("800.00"), cuentaDestinoActualizada.getBalance());
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Cuenta No Encontrada")
    class ErroresCuentaNoEncontrada {

        @Test
        @DisplayName("Debería lanzar AccountNotFoundException cuando la cuenta de origen no existe")
        void deberiaLanzarExcepcion_CuandoCuentaOrigenNoExiste() {
            UUID idInexistente = UUID.randomUUID();

            when(accountRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(AccountNotFoundException.class, () ->
                    transferService.executeTransfer(
                            idInexistente,
                            cuentaDestino.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar AccountNotFoundException cuando la cuenta de destino no existe")
        void deberiaLanzarExcepcion_CuandoCuentaDestinoNoExiste() {
            UUID idInexistente = UUID.randomUUID();

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());

            assertThrows(AccountNotFoundException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            idInexistente,
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Saldo Insuficiente")
    class ErroresSaldoInsuficiente {

        @Test
        @DisplayName("Debería lanzar InsufficientBalanceException cuando el saldo es menor al monto")
        void deberiaLanzarExcepcion_CuandoSaldoEsMenorAlMonto() {
            BigDecimal montoMayorAlSaldo = new BigDecimal("1500.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(InsufficientBalanceException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            montoMayorAlSaldo,
                            Optional.empty()
                    )
            );

            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería lanzar InsufficientBalanceException cuando el saldo es exactamente igual al monto")
        void deberiaLanzarExcepcion_CuandoSaldoEsIgualAlMonto() {
            BigDecimal montoIgualAlSaldo = new BigDecimal("1000.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(InsufficientBalanceException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            montoIgualAlSaldo,
                            Optional.empty()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Escenarios de Idempotencia")
    class EscenariosIdempotencia {

        @Test
        @DisplayName("Debería retornar transferencia existente cuando la clave de idempotencia ya existe")
        void deberiaRetornarTransferenciaExistente_CuandoClaveIdempotenciaYaExiste() {
            String claveIdempotencia = "TRANSFER-12345-CHANNEL";
            Transfer transferenciaExistente = Transfer.builder()
                    .id(UUID.randomUUID())
                    .originAccountId(cuentaOrigen.getId())
                    .destinationAccountId(cuentaDestino.getId())
                    .amount(new BigDecimal("200.00"))
                    .idempotencyKey(claveIdempotencia)
                    .createdAt(LocalDateTime.now().minusHours(1))
                    .build();

            when(idempotencyRepository.findByKey(claveIdempotencia))
                    .thenReturn(Optional.of(transferenciaExistente));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("200.00"),
                    Optional.of(claveIdempotencia)
            );

            assertNotNull(resultado);
            assertEquals(transferenciaExistente.getId(), resultado.getId());
            assertEquals(transferenciaExistente.getCreatedAt(), resultado.getCreatedAt());

            verify(accountRepository, never()).findById(any());
            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería crear nueva transferencia cuando la clave de idempotencia no existe")
        void deberiaCrearNuevaTransferencia_CuandoClaveIdempotenciaNoExiste() {
            String nuevaClaveIdempotencia = "TRANSFER-NUEVO-123";

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(nuevaClaveIdempotencia))
                    .thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("150.00"),
                    Optional.of(nuevaClaveIdempotencia)
            );

            assertNotNull(resultado);
            assertEquals(nuevaClaveIdempotencia, resultado.getIdempotencyKey());
            verify(idempotencyRepository).save(any());
        }

        @Test
        @DisplayName("Debería usar clave autogenerada cuando no se provee idempotencia")
        void deberiaUsarClaveAutogenerada_CuandoNoSeProveeIdempotencia() {
            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("100.00"),
                    Optional.empty()
            );

            assertNotNull(resultado);
            assertNotNull(resultado.getIdempotencyKey());
            assertTrue(resultado.getIdempotencyKey().startsWith("TRANSFER-"));
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación")
    class EscenariosValidacion {

        @Test
        @DisplayName("Debería lanzar excepción cuando se intenta transferir a la misma cuenta")
        void deberiaLanzarExcepcion_CuandoSeIntentaTransferirALaMismaCuenta() {
            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaOrigen.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el monto es menor o igual a cero")
        void deberiaLanzarExcepcion_CuandoMontoEsMenorOIgualACero() {
            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            BigDecimal.ZERO,
                            Optional.empty()
                    )
            );

            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            new BigDecimal("-50.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando la cuenta de origen está inactiva")
        void deberiaLanzarExcepcion_CuandoCuentaOrigenEstaInactiva() {
            Account cuentaInactiva = Account.builder()
                    .id(cuentaOrigen.getId())
                    .accountNumber(cuentaOrigen.getAccountNumber())
                    .balance(new BigDecimal("1000.00"))
                    .currency("USD")
                    .active(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaInactiva));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(IllegalStateException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }
    }
}