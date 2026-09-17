package com.bank.accounts.infrastructure.idempotency;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class IdempotencyKeyGenerator {

    private static final String PREFIX = "IDEMP";
    private static final String SEPARATOR = "|";
    private static final int MAX_OPERATION_NUMBER_LENGTH = 50;
    private static final int MAX_CHANNEL_LENGTH = 20;
    private static final Pattern VALID_CHANNEL_PATTERN = Pattern.compile("^[A-Z0-9_]{3,20}$");
    private static final Pattern VALID_OPERATION_PATTERN = Pattern.compile("^[A-Z0-9\\-]{1,50}$");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final MessageDigest sha256Digest;

    public IdempotencyKeyGenerator() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public String generateKey(String operationNumber, String canal) {
        validarParametros(operationNumber, canal);
        
        String normalizedOp = normalizeOperationNumber(operationNumber);
        String normalizedChannel = normalizeChannel(canal);
        
        String rawKey = PREFIX + SEPARATOR + normalizedChannel + SEPARATOR + 
                        normalizedOp + SEPARATOR + LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        
        return calcularHash(rawKey);
    }

    public String generate(String operationNumber, String canal) {
        return generateKey(operationNumber, canal);
    }

    public boolean isValidKeyFormat(String key) {
        if (key == null || key.isBlank()) return false;
        return key.startsWith(PREFIX) && key.length() == 71;
    }

    public String extractChannel(String key) {
        if (!isValidKeyFormat(key)) return null;
        String[] parts = key.split("\\" + SEPARATOR);
        return parts.length > 1 ? parts[1] : null;
    }

    public String extractTimestamp(String key) {
        if (!isValidKeyFormat(key)) return null;
        String[] parts = key.split("\\" + SEPARATOR);
        return parts.length > 3 ? parts[3] : null;
    }

    public boolean isExpired(String key) {
        String timestamp = extractTimestamp(key);
        if (timestamp == null) return true;
        try {
            LocalDateTime keyTime = LocalDateTime.parse(timestamp, TIMESTAMP_FORMATTER);
            return keyTime.plusHours(24).isBefore(LocalDateTime.now());
        } catch (Exception e) {
            return true;
        }
    }

    private void validarParametros(String operationNumber, String canal) {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El numero de operacion no puede ser nulo o vacio");
        }
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacio");
        }
        if (operationNumber.length() > MAX_OPERATION_NUMBER_LENGTH) {
            throw new IllegalArgumentException("El numero de operacion excede el limite de " + MAX_OPERATION_NUMBER_LENGTH + " caracteres");
        }
        if (canal.length() > MAX_CHANNEL_LENGTH) {
            throw new IllegalArgumentException("El canal excede el limite de " + MAX_CHANNEL_LENGTH + " caracteres");
        }
    }

    private String calcularHash(String entrada) {
        byte[] hash = sha256Digest.digest(entrada.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return PREFIX + SEPARATOR + hexString.toString();
    }

    public String generateTransferKey(String operationNumber, String canal, String accountId) {
        validarParametros(operationNumber, canal);
        String baseKey = generateKey(operationNumber, canal);
        return baseKey + SEPARATOR + accountId;
    }

    public String normalizeOperationNumber(String operationNumber) {
        return operationNumber != null ? operationNumber.trim().toUpperCase() : "";
    }

    public String normalizeChannel(String canal) {
        return canal != null ? canal.trim().toUpperCase() : "";
    }
}