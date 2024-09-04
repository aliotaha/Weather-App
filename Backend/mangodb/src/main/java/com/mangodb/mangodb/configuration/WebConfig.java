package com.mangodb.mangodb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS settings for the application.
     * Allows frontend applications to make requests to this backend service.
     * @param registry CORS registry to configure.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS settings to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow requests from the specified origin (e.g., React frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials such as cookies and authorization headers
    }
}
