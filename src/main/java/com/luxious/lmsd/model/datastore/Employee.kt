package com.luxious.lmsd.model.datastore

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.time.Instant

@DynamoDBTable(tableName = "EmployeeTable")
data class Employee @JvmOverloads constructor(
        @DynamoDBHashKey(attributeName = "employeeId")
        var employeeId: String? = null,

        @DynamoDBAttribute(attributeName = "employeeLogin")
        @DynamoDBIndexHashKey(globalSecondaryIndexName = EMPLOYEE_ID_INDEX)
        var employeeLogin: String? = null,

        @DynamoDBAttribute(attributeName = "name")
        var name: String? = null,

        @DynamoDBAttribute(attributeName = "email")
        var email: String? = null,

        @DynamoDBAttribute(attributeName = "createdOn")
        var createdOn: Long? = Instant.now().epochSecond
) {
    companion object {
        const val EMPLOYEE_ID_INDEX = "employeeLogin-index"
    }
}