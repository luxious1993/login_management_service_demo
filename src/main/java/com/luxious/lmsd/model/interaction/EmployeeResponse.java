package com.luxious.lmsd.model.interaction;

import com.luxious.lmsd.model.datastore.Employee;

public class EmployeeResponse {
    private Employee employee;

    public EmployeeResponse(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
