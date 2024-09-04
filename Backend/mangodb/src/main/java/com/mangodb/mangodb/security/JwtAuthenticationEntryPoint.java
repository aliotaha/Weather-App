package com.mangodb.mangodb.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Custom AuthenticationEntryPoint to handle unauthorized access attempts.
 * This component is triggered when an unauthenticated request is made to a secured endpoint.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Handles unauthorized access attempts by sending an HTTP 401 Unauthorized response.
     *
     * @param request The HTTP request that resulted in an AuthenticationException.
     * @param response The HTTP response to send back to the client.
     * @param authException The exception that caused the unauthorized access attempt.
     * @throws IOException If an input or output exception occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
            throws IOException, ServletException {
        // Set HTTP response status to 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        // Write an error message to the response
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
    }
}
