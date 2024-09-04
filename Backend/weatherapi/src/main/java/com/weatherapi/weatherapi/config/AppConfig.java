package com.weatherapi.weatherapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

/**
 * Configuration class for the Weather API application.
 * This class contains bean definitions and configuration settings for various components used in the application.
 */
@Configuration // Indicates that this class contains Spring configuration and bean definitions.
public class AppConfig {

    /**
     * Configures and provides a RestTemplate bean.
     * RestTemplate is used to make HTTP requests to external services.
     * 
     * @param builder RestTemplateBuilder used to configure the RestTemplate instance.
     * @return A configured RestTemplate instance with custom connection and read timeouts.
     */
    @Bean // Marks this method as a bean producer for the Spring application context.
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5)) // Sets the connection timeout to 5 seconds.
                .setReadTimeout(Duration.ofSeconds(5)) // Sets the read timeout to 5 seconds.
                .build(); // Builds and returns the RestTemplate instance.
    }
}

