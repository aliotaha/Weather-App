// src/components/Register.js

import React, { useState } from 'react';
import { register } from '../services/authService'; // Import the register function from authService
import { useNavigate } from 'react-router-dom'; // Import useNavigate for redirection
import './AuthStyles.css'; // Import the CSS file for styling

const Register = () => {
    const [username, setUsername] = useState(''); // State to store the username
    const [password, setPassword] = useState(''); // State to store the password
    const [message, setMessage] = useState(''); // State to store registration success message
    const [error, setError] = useState(''); // State to store registration error message
    const navigate = useNavigate(); // Initialize useNavigate hook for redirection

    // Function to handle form submission
    const handleRegister = async (e) => {
        e.preventDefault(); // Prevent the default form submission behavior
        try {
            const result = await register(username, password); // Call the register function from authService
            setMessage(result); // Set the success message on successful registration
            setError(''); // Clear any previous error messages
            // Show the login button after successful registration
        } catch (error) {
            setError(error.data); // Set the error message on failure
            setMessage(''); // Clear any previous success messages
        }
    };

    // Function to redirect to the login page
    const handleLoginRedirect = () => {
        navigate('/login'); // Redirect to the login page
    };

    return (
        <div className="container">
            <div className="form-container">
                <h2>Register</h2>
                <form onSubmit={handleRegister}>
                    <div className="form-group">
                        <label>Username</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)} // Update username state on input change
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)} // Update password state on input change
                            required
                        />
                    </div>
                    <button type="submit">Register</button>
                </form>
                {message && (
                    <div>
                        <p>{message}</p> {/* Display registration success message */}
                        <button onClick={handleLoginRedirect}>Login</button> {/* Redirect to login page */}
                    </div>
                )}
                {error && <p className="error-message">{error}</p>} {/* Display registration error message */}
            </div>
        </div>
    );
};

export default Register;
