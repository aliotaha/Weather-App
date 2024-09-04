package com.mangodb.mangodb.controller;

/**
 * Model representing the authentication details of a user.
 * Used for login and token generation.
 */
public class AuthenticationModel {

    private String username;
    private String password;
    private String jwtToken;

    // Default constructor
    public AuthenticationModel() {
    }

    // Parameterized constructor
    public AuthenticationModel(String username, String password, String jwtToken) {
        this.username = username;
        this.password = password;
        this.jwtToken = jwtToken;
    }

    /**
     * Gets the username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the JWT token.
     * @return the JWT token.
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets the JWT token.
     * @param jwtToken the JWT token to set.
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    /**
     * Creates a builder for the AuthenticationModel.
     * @return a new AuthenticationModelBuilder.
     */
    public static AuthenticationModelBuilder builder() {
        return new AuthenticationModelBuilder();
    }

    /**
     * Builder class for creating an instance of AuthenticationModel.
     */
    public static class AuthenticationModelBuilder {
        private String username;
        private String password;
        private String jwtToken;

        /**
         * Sets the username for the builder.
         * @param username the username to set.
         * @return the builder instance.
         */
        public AuthenticationModelBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the password for the builder.
         * @param password the password to set.
         * @return the builder instance.
         */
        public AuthenticationModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the JWT token for the builder.
         * @param jwtToken the JWT token to set.
         * @return the builder instance.
         */
        public AuthenticationModelBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        /**
         * Builds and returns an AuthenticationModel instance.
         * @return a new AuthenticationModel.
         */
        public AuthenticationModel build() {
            return new AuthenticationModel(username, password, jwtToken);
        }
    }
}
