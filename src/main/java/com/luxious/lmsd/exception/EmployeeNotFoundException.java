package com.luxious.lmsd.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    public EmployeeNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
