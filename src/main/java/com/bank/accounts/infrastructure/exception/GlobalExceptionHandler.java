package com.bank.accounts.infrastructure.exception;

import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException ex, WebRequest request) {
        logger.warn("Cuenta no encontrada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("NOT_FOUND")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(
            InsufficientBalanceException ex, WebRequest request) {
        logger.warn("Saldo insuficiente: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("INSUFFICIENT_BALANCE")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateAccountException(
            DuplicateAccountException ex, WebRequest request) {
        logger.warn("Cuenta duplicada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD_REQUEST")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
                fieldErrors.put(error.getField(), error.getDefaultMessage()));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("VALIDATION_ERROR")
                .message("Error en la validación de los datos de entrada")
                .path(request.getDescription(false).replace("uri=", ""))
                .fieldErrors(fieldErrors)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("INTERNAL_SERVER_ERROR")
                .message("Error interno del servidor. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        private ErrorResponse(Builder builder) {
            this.timestamp = builder.timestamp;
            this.status = builder.status;
            this.error = builder.error;
            this.message = builder.message;
            this.path = builder.path;
        }

        public static Builder builder() {
            return new Builder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public static class Builder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder status(int status) {
                this.status = status;
                return this;
            }

            public Builder error(String error) {
                this.error = error;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                return new ErrorResponse(this);
            }
        }
    }

    public static class ValidationErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;
        private final Map<String, String> fieldErrors;

        private ValidationErrorResponse(Builder builder) {
            this.timestamp = builder.timestamp;
            this.status = builder.status;
            this.error = builder.error;
            this.message = builder.message;
            this.path = builder.path;
            this.fieldErrors = builder.fieldErrors;
        }

        public static Builder builder() {
            return new Builder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public Map<String, String> getFieldErrors() {
            return fieldErrors;
        }

        public static class Builder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private Map<String, String> fieldErrors;

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder status(int status) {
                this.status = status;
                return this;
            }

            public Builder error(String error) {
                this.error = error;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder path(String path) {
                this.path = path;
                return this;
            }

            public Builder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                return new ValidationErrorResponse(this);
            }
        }
    }
}