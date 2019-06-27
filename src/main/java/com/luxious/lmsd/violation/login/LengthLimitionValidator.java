package com.luxious.lmsd.violation.login;

import com.luxious.lmsd.model.service.LoginValidationResult;
import com.luxious.lmsd.model.service.LoginViolationType;
import org.springframework.stereotype.Component;

@Component
public class LengthLimitionValidator implements LoginValidator {
    private static final int MAX_LENGTH = 9;
    private static final int MIN_LENGTH = 2;
    @Override
    public LoginValidationResult validate(String employeeLogin) {
        if (employeeLogin.length() >= MIN_LENGTH && employeeLogin.length() <= MAX_LENGTH) {
            return LoginValidationResult.validResult();
        } else {
            return LoginValidationResult.withViolationResult(LoginViolationType.LENGTH_LIMITION);
        }
    }
}
