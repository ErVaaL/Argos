package com.erval.argos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot entrypoint scanning all Argos packages (API, application, adapters).
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.erval.argos")
public class ArgosApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArgosApiApplication.class, args);
    }
}
