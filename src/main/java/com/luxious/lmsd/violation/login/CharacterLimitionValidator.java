package com.luxious.lmsd.violation.login;

import com.luxious.lmsd.model.service.LoginValidationResult;
import com.luxious.lmsd.model.service.LoginViolationType;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CharacterLimitionValidator implements LoginValidator {
    private static final Pattern ALLOWED_PATTERN = Pattern.compile("[a-z0-9]+");

    @Override
    public LoginValidationResult validate(String employeeLogin) {
        if (ALLOWED_PATTERN.matcher(employeeLogin).matches()) {
            return LoginValidationResult.validResult();
        } else {
            return LoginValidationResult.withViolationResult(LoginViolationType.CHARARCTER_LIMITION);
        }
    }
}
