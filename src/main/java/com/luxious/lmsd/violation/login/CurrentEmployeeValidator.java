package com.luxious.lmsd.violation.login;

import com.luxious.lmsd.dao.EmployeeDao;
import com.luxious.lmsd.model.datastore.Employee;
import com.luxious.lmsd.model.service.LoginValidationResult;
import com.luxious.lmsd.model.service.LoginViolationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentEmployeeValidator implements LoginValidator {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public LoginValidationResult validate(String employeeLogin) {
        System.out.println(employeeLogin);
        List<Employee> employeeList = employeeDao.getEmployeeByEmployeeLogin(employeeLogin);
        System.out.println(employeeList.size());
        if (employeeList.isEmpty()) {
            return LoginValidationResult.validResult();
        } else {
            return LoginValidationResult.withViolationResult(LoginViolationType.CURRENT_EMPLOYEE);
        }
    }
}
