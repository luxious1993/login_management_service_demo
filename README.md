# login_management_service_demo

Web application with RESTful APIs

Used Spring Boot + DynamoDB + Swagger
Used DynamoDBMapper for data changes in table instead of Repository in spring (better for native aws operations)

Using step:
1. Update your aws credential firstly at src/main/resource/application.properties and it will bind in my AWSConfig
2. Create dynamodb referred by src/main/java/com/luxious/lmsd/model/datastore
    The primary key(Hash key) is employeeId(String)
    The second index Hash key is employeeLogin(String)
3. Start your app and open swagger-ui: http://localhost:8080/swagger-ui.html
   There will be serveral controllers:
        EmployeeController: create/update/delete/get an employee
        Will Do: get a suggested login
                 check a login available and make login unique

Any question about aws credential : 
https://docs.aws.amazon.com/zh_cn/amazondynamodb/latest/developerguide/SettingUp.DynamoWebService.html

Only finished Employee RESTful APIs, will do login_generator, login_validation, login_suggestion

Author: liuxin
