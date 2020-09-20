package com.senac.assister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AssisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssisterApplication.class, args);
    }
}
