package com.mangodb.mangodb.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom filter to validate JWT tokens and set the authentication in the security context.
 * This filter is executed once per request to ensure that the JWT token is valid and the user is authenticated.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Processes the JWT token from the request header and sets the authentication in the security context if valid.
     *
     * @param request The HTTP request containing the JWT token.
     * @param response The HTTP response to send to the client.
     * @param filterChain The filter chain to continue the request processing.
     * @throws ServletException If an error occurs during filter processing.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7); // Extract the token from the header
            try {
                // Extract username from the JWT token
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.error("Illegal Argument while fetching the username", e);
            } catch (ExpiredJwtException e) {
                logger.error("JWT token is expired", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token", e);
            } catch (Exception e) {
                logger.error("JWT token processing error", e);
            }
        } else {
            logger.warn("Invalid Authorization Header Value");
        }

        // If username is not null and no authentication is set in the security context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details using the username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // Validate the token
            boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                // If token is valid, set the authentication in the security context
                UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("JWT token validation failed");
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
