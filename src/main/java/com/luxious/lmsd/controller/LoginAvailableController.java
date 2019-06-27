package com.luxious.lmsd.controller;

import com.luxious.lmsd.model.interaction.LoginAvailableResponse;
import com.luxious.lmsd.model.service.LoginViolationType;
import com.luxious.lmsd.utils.LoginAvailableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.EnumSet;

@RestController
@RequestMapping(value = "/loginAvailable")
public class LoginAvailableController {
    @Autowired
    LoginAvailableHandler loginAvailableHandler;

    @GetMapping(value = "/{employeeLogin}")
    public LoginAvailableResponse getLoginAvailable(
            @PathVariable("employeeLogin") @NotNull final String employeeLogin) {
        final EnumSet<LoginViolationType> violations =
                loginAvailableHandler.getLoginValidationResult(employeeLogin);
        return new LoginAvailableResponse(violations);
    }
}
