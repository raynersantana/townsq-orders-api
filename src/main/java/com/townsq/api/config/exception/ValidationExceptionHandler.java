package com.townsq.api.config.exception;

public class ValidationExceptionHandler extends RuntimeException {
    public ValidationExceptionHandler(String message) {
        super(message);
    }
}
