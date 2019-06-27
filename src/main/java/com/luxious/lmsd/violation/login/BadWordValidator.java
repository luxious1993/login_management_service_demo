package com.luxious.lmsd.violation.login;

import com.luxious.lmsd.dao.S3FileReader;
import com.luxious.lmsd.model.service.LoginValidationResult;
import com.luxious.lmsd.model.service.LoginViolationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BadWordValidator implements LoginValidator {
    @Autowired
    S3FileReader s3FileReader;

    @Override
    public LoginValidationResult validate(String employeeLogin) {
        Set<String> badLogins = s3FileReader.getBadWordLogins();
        if (badLogins.contains(employeeLogin)) {
            return LoginValidationResult.validResult();
        } else {
            return LoginValidationResult.withViolationResult(LoginViolationType.BAD_WORD);
        }
    }
}
