package com.luxious.lmsd.Exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    public EmployeeNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
