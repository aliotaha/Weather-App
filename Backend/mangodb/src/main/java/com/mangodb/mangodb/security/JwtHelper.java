package com.mangodb.mangodb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for handling JWT (JSON Web Token) operations.
 * This includes generating, parsing, and validating JWT tokens.
 */
@Component
public class JwtHelper {

    // Token validity duration in seconds (5 hours)
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // Secret key for signing JWT tokens
    private final SecretKey secretKey;

    /**
     * Constructor to initialize the secret key for JWT.
     * The key is generated using the HS512 algorithm.
     */
    public JwtHelper() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token The JWT token.
     * @param claimsResolver A function to extract the claim from the token's claims.
     * @param <T> The type of the claim.
     * @return The extracted claim.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieves all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The claims contained in the token.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the JWT token has expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, otherwise false.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generates a JWT token with the given username.
     *
     * @param username The username for which the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    /**
     * Creates a JWT token with the specified claims and subject.
     *
     * @param claims A map of claims to include in the token.
     * @param subject The subject (usually the username) of the token.
     * @return The generated JWT token.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token.
     * @param userDetails The user details to validate the token against.
     * @return True if the token is valid, otherwise false.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
