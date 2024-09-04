package com.mangodb.mangodb.service;

import com.mangodb.mangodb.exception.UserAlreadyExistsException;
import com.mangodb.mangodb.model.User;
import com.mangodb.mangodb.model.RegistrationModel;
import com.mangodb.mangodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service class for user-related operations.
 * Handles user authentication and registration.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor to initialize UserService with UserRepository and PasswordEncoder.
     *
     * @param userRepository Repository for user data operations.
     * @param passwordEncoder Encoder for password hashing and comparison.
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user based on username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user.
     * @throws BadCredentialsException If the user is not found or password is incorrect.
     */
    public User authenticateUser(String username, String password) {
        // Fetch user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        // Check if the provided password matches the stored password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Return the authenticated user
        return user;
    }

    /**
     * Registers a new user.
     *
     * @param registrationModel Contains user details for registration.
     * @throws UserAlreadyExistsException If a user with the same username already exists.
     */
    public void registerUser(RegistrationModel registrationModel) {
        // Check if the username already exists
        if (userRepository.findByUsername(registrationModel.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(registrationModel.getUsername());
        }

        // Create a new user
        User user = new User();
        user.setUsername(registrationModel.getUsername());
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
        user.setRoles(Collections.singletonList("USER")); // Assign default role
        
        // Save the new user to the repository
        userRepository.save(user);
    }
}
