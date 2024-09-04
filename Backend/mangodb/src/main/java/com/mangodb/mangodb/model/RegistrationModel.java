package com.mangodb.mangodb.model;

/**
 * Represents the data required for user registration.
 */
public class RegistrationModel {

    private String username; // Username for registration
    private String password; // Password for registration

    // Default constructor
    public RegistrationModel() {
    }

    /**
     * Constructs a new RegistrationModel with the specified username and password.
     * @param username the username for registration.
     * @param password the password for registration.
     */
    public RegistrationModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username for registration.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for registration.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for registration.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for registration.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Provides a builder for creating instances of RegistrationModel.
     * @return a new RegistrationModelBuilder.
     */
    public static RegistrationModelBuilder builder() {
        return new RegistrationModelBuilder();
    }

    /**
     * Builder class for creating instances of RegistrationModel.
     */
    public static class RegistrationModelBuilder {
        private String username;
        private String password;

        /**
         * Sets the username for the RegistrationModel.
         * @param username the username to set.
         * @return the builder instance.
         */
        public RegistrationModelBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the password for the RegistrationModel.
         * @param password the password to set.
         * @return the builder instance.
         */
        public RegistrationModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Builds and returns a new RegistrationModel instance.
         * @return the new RegistrationModel.
         */
        public RegistrationModel build() {
            return new RegistrationModel(username, password);
        }
    }
}
