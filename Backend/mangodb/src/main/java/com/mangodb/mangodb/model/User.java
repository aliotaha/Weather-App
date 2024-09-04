package com.mangodb.mangodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * Represents a user in the MongoDB database.
 * This class maps to the "users" collection.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id; // Unique identifier for the user

    private String username; // Username for the user

    private String password; // Password for the user (should be encrypted)

    private List<String> roles; // Roles assigned to the user (e.g., "USER", "ADMIN")

    // Default constructor
    public User() {
    }

    /**
     * Constructs a new User with the specified username, password, and roles.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param roles the roles assigned to the user.
     */
    public User(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Gets the unique identifier of the user.
     * @return the ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * @param id the ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the roles assigned to the user.
     * @return the list of roles.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the user.
     * @param roles the roles to set.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
