package com.mangodb.mangodb.service;

import com.mangodb.mangodb.model.User;
import com.mangodb.mangodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class to load user-specific data.
 * Implements UserDetailsService to provide user details for authentication.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor to initialize MongoUserDetailsService with UserRepository.
     *
     * @param userRepository Repository to interact with user data.
     */
    @Autowired
    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user-specific data by username.
     *
     * @param username The username of the user to be loaded.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException If the user is not found with the provided username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Build and return UserDetails object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0])) // Convert roles list to array
                .build();
    }
}
