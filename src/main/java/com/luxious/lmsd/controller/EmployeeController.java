package com.luxious.lmsd.controller;

import com.luxious.lmsd.Exception.EmployeeIdAlreadyExistException;
import com.luxious.lmsd.Exception.EmployeeNotFoundException;
import com.luxious.lmsd.dao.EmployeeDao;
import com.luxious.lmsd.model.datastore.Employee;
import com.luxious.lmsd.model.service.CreateEmployeeRequest;
import com.luxious.lmsd.model.service.EmployeeResponse;
import com.luxious.lmsd.model.service.UpdateEmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employeeController")
public class EmployeeController {
    private static final String EMPLOYEE_ID = "employeeId";
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(value = "/{employeeId}")
    public EmployeeResponse getEmployeeByEmployeeId(@PathVariable(EMPLOYEE_ID) final String employeeId) {
        final Employee employee = getEmployee(employeeId);
        return new EmployeeResponse(employee);
    }

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody final CreateEmployeeRequest request) {
        String employeeId = UUID.randomUUID().toString();
        while (employeeDao.getEmployeeByEmployeeId(employeeId) != null) {
            employeeId = UUID.randomUUID().toString();
        }
        // TODO: validate login
        final String employeeLogin = request.getEmployeeLogin().trim().toLowerCase();
        final String name = request.getName().trim().toLowerCase();
        final String email = request.getEmail().trim().toLowerCase();
        final Employee employee = new Employee(employeeId, employeeLogin, name, email);
        try {
            employeeDao.createEmployee(employee);
        } catch (EmployeeIdAlreadyExistException e) {
            throw new BadRequestException(String.format("employeeId is already exist", employeeId), e);
        }
        return new EmployeeResponse(employee);
    }

    @DeleteMapping(value = "/{employeeId}")
    public Response deleteEmployee(@PathVariable(EMPLOYEE_ID) final String employeeId) {
        final Employee employee = getEmployee(employeeId);
        employeeDao.deleteEmployee(employee);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PutMapping(value = "/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable(EMPLOYEE_ID) final String employeeId,
                                           @RequestBody final UpdateEmployeeRequest request) {
        final String newEmployeeId = sanitizeId(employeeId);
        // TODO: validate login
        final String newEmployeeLogin = request.getEmployeeLogin().trim().toLowerCase();
        final String newName = request.getName().trim().toLowerCase();
        final String newEmail = request.getEmail().trim().toLowerCase();
        final Employee newEmployee = new Employee(newEmployeeId, newEmployeeLogin, newName, newEmail);
        try {
            employeeDao.updateEmployee(newEmployee);
        } catch (EmployeeNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
        return new EmployeeResponse(newEmployee);
    }

    private String sanitizeId(String employeeId) {
        if (StringUtils.isBlank(employeeId)) {
            throw new BadRequestException("employeeId cannot be empty");
        }
        return employeeId.trim().toLowerCase();
    }

    private Employee getEmployee(final String employeeId) {
        final String id = sanitizeId(employeeId);
        final Employee employee = employeeDao.getEmployeeByEmployeeId(id);
        if (employee == null) {
            throw new NotFoundException(String.format("employeeId: %s is not exist", employeeId));
        }
        return employee;
    }
}
