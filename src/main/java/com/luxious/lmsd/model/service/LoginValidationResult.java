package com.luxious.lmsd.model.service;

import javax.validation.constraints.NotNull;
import java.util.EnumSet;

public class LoginValidationResult {
    private final boolean isValid;
    private final EnumSet<LoginViolationType> loginViolationTypes;

    private LoginValidationResult(@NotNull final boolean isValid,
                                  @NotNull final EnumSet<LoginViolationType> loginViolationTypes) {
        this.isValid = isValid;
        this.loginViolationTypes = loginViolationTypes;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public EnumSet<LoginViolationType> getValidations() {
        return this.loginViolationTypes;
    }

    public static LoginValidationResult validResult() {
        return new LoginValidationResult(true, EnumSet.noneOf(LoginViolationType.class));
    }

    public static LoginValidationResult withViolationResult(final LoginViolationType loginViolation) {
        return new LoginValidationResult(false, EnumSet.of(loginViolation));
    }

    public static LoginValidationResult withViolationsResult(final EnumSet<LoginViolationType> loginViolations) {
        return new LoginValidationResult(false, EnumSet.copyOf(loginViolations));
    }
}
