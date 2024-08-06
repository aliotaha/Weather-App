// src/services/authService.js

import axios from 'axios';

const API_URL = 'http://localhost:9090/auth';

// Function to log in and get JWT token
export const login = async (username, password) => {
    try {
        const response = await axios.post(`${API_URL}/login`, { username, password });
        localStorage.setItem('authToken', response.data.jwtToken); // Store token in local storage
        return response.data; // Returns { username, jwtToken }
    } catch (error) {
        throw error.response.data;
    }
};

// Function to register a new user
export const register = async (username, password) => {
    try {
        const response = await axios.post(`${API_URL}/register`, { username, password });
        return response.data; // Success message
    } catch (error) {
        throw error.response.data;
    }
};

// Function to log out
export const logout = () => {
    localStorage.removeItem('authToken'); // Remove token from local storage
};
