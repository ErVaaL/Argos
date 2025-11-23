package com.erval.argos.api;

import com.erval.argos.m2m.config.InternalClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot entrypoint scanning all Argos packages (API, application, adapters).
 */
@SpringBootApplication
@EnableConfigurationProperties(InternalClientProperties.class)
@ComponentScan(basePackages = "com.erval.argos")
public class ArgosApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArgosApiApplication.class, args);
    }
}
