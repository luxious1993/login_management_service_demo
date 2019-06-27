package com.luxious.lmsd.violation.login;

import com.luxious.lmsd.model.service.LoginValidationResult;

public interface LoginValidator {
    LoginValidationResult validate(final String employeeLogin);
}
