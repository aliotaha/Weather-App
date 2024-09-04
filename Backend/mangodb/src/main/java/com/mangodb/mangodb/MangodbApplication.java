package com.mangodb.mangodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 * This class is responsible for starting the application.
 */
@SpringBootApplication
public class MangodbApplication {

    /**
     * Main method to run the Spring Boot application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MangodbApplication.class, args);
    }
}
