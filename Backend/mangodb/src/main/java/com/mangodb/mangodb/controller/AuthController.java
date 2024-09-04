package com.mangodb.mangodb.controller;

import com.mangodb.mangodb.model.RegistrationModel;
import com.mangodb.mangodb.model.User;
import com.mangodb.mangodb.security.JwtHelper;
import com.mangodb.mangodb.service.UserService;
import com.mangodb.mangodb.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication-related operations.
 * Provides endpoints for user login, registration, and exception handling.
 */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Handles user login requests.
     * Authenticates the user and returns a JWT token upon successful authentication.
     * @param authModel The authentication model containing username and password.
     * @return ResponseEntity containing the authentication model with JWT token or an unauthorized status.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationModel> login(@RequestBody AuthenticationModel authModel) {
        try {
            User user = userService.authenticateUser(authModel.getUsername(), authModel.getPassword());
            String token = jwtHelper.generateToken(user.getUsername());

            AuthenticationModel response = AuthenticationModel.builder()
                    .username(user.getUsername())
                    .jwtToken(token)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            logger.error("Invalid Username or Password", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Handles user registration requests.
     * Registers a new user with the provided registration details.
     * @param registrationModel The registration model containing user details.
     * @return ResponseEntity indicating successful registration or an error message.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationModel registrationModel) {
        userService.registerUser(registrationModel);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    /**
     * Handles exceptions for user already exists scenario.
     * @param ex The exception thrown when a user already exists.
     * @return ResponseEntity with error message and bad request status.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions for invalid credentials.
     * @param ex The exception thrown when credentials are invalid.
     * @return ResponseEntity with error message and unauthorized status.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Credentials Invalid", HttpStatus.UNAUTHORIZED);
    }
}
