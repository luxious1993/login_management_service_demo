package com.luxious.lmsd.model.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateEmployeeRequest {
    private String employeeId;
    private String employeeLogin;
    private String name;
    private String email;

    @JsonCreator
    CreateEmployeeRequest(@JsonProperty("employeeId") String employeeId,
                          @JsonProperty("employeeLogin") String employeeLogin,
                          @JsonProperty("name") String name,
                          @JsonProperty("email") String email) {
        this.employeeId = employeeId;
        this.employeeLogin = employeeLogin;
        this.name = name;
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
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
