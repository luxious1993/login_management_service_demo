package com.luxious.lmsd.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.luxious.lmsd.Exception.EmployeeIdAlreadyExistException;
import com.luxious.lmsd.Exception.EmployeeNotFoundException;
import com.luxious.lmsd.model.datastore.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

import static com.luxious.lmsd.model.datastore.Employee.EMPLOYEE_ID_INDEX;

@Component
public class EmployeeDao {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    public Employee getEmployeeByEmployeeId(@NotNull final String employeeId) {
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public List<Employee> getEmployeeByEmployeeLogin(@NotNull final String employeeLogin) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1",  new AttributeValue().withS(employeeLogin));
        DynamoDBQueryExpression<Employee> queryExpression = new DynamoDBQueryExpression<Employee>()
                .withIndexName(EMPLOYEE_ID_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("employeeLogin = :v1")
                .withExpressionAttributeValues(eav);
        return dynamoDBMapper.query(Employee.class, queryExpression);
    }

    public void createEmployee(@NotNull final Employee employee) {
        try {
            dynamoDBMapper.save(employee);
        } catch (ConditionalCheckFailedException e) {
            throw new EmployeeIdAlreadyExistException(
                    employee.getEmployeeId() + " is already existed, cannot save again", e);
        }
    }

    public void deleteEmployee(@NotNull final Employee employee) {
        dynamoDBMapper.delete(employee);
    }

    public void updateEmployee(@NotNull final Employee employee) {
        final String employeeId = employee.getEmployeeId();
        final Employee oldEmployee = getEmployeeByEmployeeId(employeeId);
        if (oldEmployee == null) {
            throw new EmployeeNotFoundException(String.format("EmployeeId: %s is not exist", employee));
        }
        deleteEmployee(oldEmployee);
        createEmployee(employee);
    }
}
