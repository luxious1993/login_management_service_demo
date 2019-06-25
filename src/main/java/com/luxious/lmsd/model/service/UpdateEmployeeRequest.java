package com.luxious.lmsd.model.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateEmployeeRequest {
    private String employeeLogin;
    private String name;
    private String email;

    @JsonCreator
    UpdateEmployeeRequest(@JsonProperty("employeeLogin") String employeeLogin,
                          @JsonProperty("name") String name,
                          @JsonProperty("email") String email) {
        this.employeeLogin = employeeLogin;
        this.name = name;
        this.email = email;
    }

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
