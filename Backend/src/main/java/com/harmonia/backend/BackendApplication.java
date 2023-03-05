package com.harmonia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Harmonia backend application.
 * This class starts the Spring Boot application and initializes the Spring application context.
 *
 * @author Harmonia Team
 */
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

    }
}
