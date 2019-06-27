package com.luxious.lmsd.violation;

import com.luxious.lmsd.violation.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LoginViolationChecker {

    @Autowired
    CharacterLimitionValidator characterLimitionValidator;
    @Autowired
    CurrentEmployeeValidator currentEmployeeValidator;
    @Autowired
    LengthLimitionValidator lengthLimitionValidator;
    @Autowired
    BadWordValidator badWordValidator;

    public List<LoginValidator> getViolations() {
        return Arrays.asList(characterLimitionValidator,
                             currentEmployeeValidator,
                             lengthLimitionValidator,
                             badWordValidator);
    }
}
