package com.springboot.sample.exception;

public class DaoRuntimeException extends RuntimeException {
    public DaoRuntimeException(String message) {
        super(message);
    }
}
