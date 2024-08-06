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

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationModel registrationModel) {
        userService.registerUser(registrationModel);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Credentials Invalid", HttpStatus.UNAUTHORIZED);
    }
}
