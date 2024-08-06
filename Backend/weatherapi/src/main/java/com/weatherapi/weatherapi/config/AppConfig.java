package com.weatherapi.weatherapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }
}
//The AppConfig class configures a RestTemplate with specific connection and read timeout settings.
//It centralizes the creation and configuration of the RestTemplate, ensuring that all HTTP requests made using this RestTemplate adhere to the defined timeout rules.
//This helps improve the resilience and reliability of the application when interacting with external services.