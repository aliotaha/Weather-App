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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return user;
    }

    public void registerUser(RegistrationModel registrationModel) {
        if (userRepository.findByUsername(registrationModel.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(registrationModel.getUsername());
        }

        User user = new User();
        user.setUsername(registrationModel.getUsername());
        user.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
        user.setRoles(Collections.singletonList("USER"));
        userRepository.save(user);
    }
}
