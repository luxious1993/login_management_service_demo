package com.luxious.lmsd.exception;

public class EmployeeIdAlreadyExistException extends RuntimeException {
    public EmployeeIdAlreadyExistException(String message) {
        super(message);
    }
    public EmployeeIdAlreadyExistException(String message, Throwable t) {
        super(message, t);
    }
}
