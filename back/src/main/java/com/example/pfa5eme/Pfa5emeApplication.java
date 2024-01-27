package com.example.pfa5eme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Pfa5emeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pfa5emeApplication.class, args);
    }

}
