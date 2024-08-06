package com.mangodb.mangodb.controller;

public class AuthenticationModel {

    private String username;
    private String password;
    private String jwtToken;

    public AuthenticationModel() {
    }

    public AuthenticationModel(String username, String password, String jwtToken) {
        this.username = username;
        this.password = password;
        this.jwtToken = jwtToken;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static AuthenticationModelBuilder builder() {
        return new AuthenticationModelBuilder();
    }

    public static class AuthenticationModelBuilder {
        private String username;
        private String password;
        private String jwtToken;

        public AuthenticationModelBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationModelBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public AuthenticationModel build() {
            return new AuthenticationModel(username, password, jwtToken);
        }
    }
}
