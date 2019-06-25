package com.luxious.lmsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.luxious.lmsd")
public class LoginManagementServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginManagementServiceDemoApplication.class, args);
    }
}
