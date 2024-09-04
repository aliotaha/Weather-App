package com.mangodb.mangodb.exception;

/**
 * Exception thrown when attempting to register a user with a username
 * that already exists in the system.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExistsException with a detailed message.
     * 
     * @param username the username that already exists.
     */
    public UserAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}
