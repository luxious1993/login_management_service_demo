package com.luxious.lmsd.utils;

import com.luxious.lmsd.model.service.LoginViolationType;
import com.luxious.lmsd.violation.LoginViolationChecker;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.stream.Collectors;

@Component
public class LoginAvailableHandler {
    @Autowired
    LoginViolationChecker loginViolationChecker;

    public EnumSet<LoginViolationType> getLoginValidationResult(@NonNull final String employeeLogin) {
        final EnumSet<LoginViolationType> validations =
                loginViolationChecker.getViolations().stream()
                .map(loginValidator -> loginValidator.validate(employeeLogin))
                .filter(validationResult -> !validationResult.isValid())
                .flatMap(validationResult -> validationResult.getValidations().stream())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(LoginViolationType.class)));
        return EnumSet.copyOf(validations);
    }

    public boolean isLoginValid(@NonNull final String employeeLogin) {
        return getLoginValidationResult(employeeLogin).isEmpty();
    }
}
