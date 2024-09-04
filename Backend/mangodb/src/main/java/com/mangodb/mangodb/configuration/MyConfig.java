package com.mangodb.mangodb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for security-related beans.
 */
@Configuration
public class MyConfig {

    /**
     * Bean for encoding passwords using BCrypt.
     * BCrypt is a strong hashing algorithm that includes a salt to protect against rainbow table attacks.
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for managing authentication.
     * This manager is used for authentication operations in Spring Security.
     * @param configuration AuthenticationConfiguration object.
     * @return AuthenticationManager instance.
     * @throws Exception if there is an issue retrieving the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
