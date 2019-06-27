package com.luxious.lmsd.model.interaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxious.lmsd.model.service.LoginViolationType;

import java.util.EnumSet;

public class LoginAvailableResponse {
    private boolean isValid;
    private EnumSet<LoginViolationType> violations;
    @JsonCreator
    public LoginAvailableResponse(@JsonProperty("loginAvaliable") final boolean isValid,
                                  @JsonProperty("violations") final EnumSet<LoginViolationType> violations) {
        this.isValid = isValid;
        this.violations = EnumSet.copyOf(violations);
    }
    public LoginAvailableResponse(final EnumSet<LoginViolationType> violations) {
        this(violations.isEmpty(), EnumSet.copyOf(violations));
    }

    public boolean isValid() {
        return isValid;
    }

    public EnumSet<LoginViolationType> getViolations() {
        return violations;
    }
}
