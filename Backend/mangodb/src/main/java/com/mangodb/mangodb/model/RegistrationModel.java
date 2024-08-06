package com.mangodb.mangodb.model;

public class RegistrationModel {

    private String username;
    private String password;

    public RegistrationModel() {
    }

    public RegistrationModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static RegistrationModelBuilder builder() {
        return new RegistrationModelBuilder();
    }

    public static class RegistrationModelBuilder {
        private String username;
        private String password;

        public RegistrationModelBuilder username(String username) {
            this.username = username;
            return this;
        }

        public RegistrationModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegistrationModel build() {
            return new RegistrationModel(username, password);
        }
    }
}
