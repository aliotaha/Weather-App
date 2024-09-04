// src/components/Login.js

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for redirection
import { login } from '../services/authService'; // Import login function from authService
import './AuthStyles.css'; // Import CSS file for styling

const Login = () => {
    // State hooks for managing username, password, and error messages
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    // Initialize useNavigate hook for navigation
    const navigate = useNavigate();

    // Function to handle form submission
    const handleLogin = async (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        try {
            // Attempt to log in using the username and password
            const result = await login(username, password);
            // Store the JWT token in localStorage if login is successful
            localStorage.setItem('jwtToken', result.jwtToken);
            setError(''); // Clear any previous error messages
            // Redirect to the weather page or another protected page
            navigate('/weather');
        } catch (error) {
            // Set error message if login fails
            setError('Invalid username or password');
        }
    };

    return (
        <div className="container">
            <div className="form-container">
                <h2>Login</h2> {/* Heading for the login form */}
                <form onSubmit={handleLogin}> {/* Handle form submission */}
                    <div className="form-group">
                        <label>Username</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)} // Update username state on change
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)} // Update password state on change
                            required
                        />
                    </div>
                    <button type="submit">Login</button> {/* Submit button */}
                </form>
                {error && <p className="error-message">{error}</p>} {/* Display error message if any */}
            </div>
        </div>
    );
};

export default Login;
