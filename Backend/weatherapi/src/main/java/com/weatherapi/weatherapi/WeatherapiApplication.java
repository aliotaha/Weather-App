package com.weatherapi.weatherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main entry point for the Weather API Spring Boot application.
 * This class is responsible for bootstrapping the application,
 * configuring the necessary Spring Boot components, and enabling specific features like MongoDB repositories and scheduling.
 */
@SpringBootApplication // Marks this class as a Spring Boot application and triggers auto-configuration, component scanning, etc.
@EnableMongoRepositories(basePackages = "com.weatherapi.weatherapi.repository") // Enables the creation of MongoDB repositories in the specified package.
@EnableScheduling // Enables support for scheduled tasks within the application.
public class WeatherapiApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     * This method delegates to Spring Boot's SpringApplication.run method to launch the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(WeatherapiApplication.class, args); // Starts the Spring Boot application.
    }
}
