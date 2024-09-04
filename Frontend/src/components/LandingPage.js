// src/components/LandingPage.js

import React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import './LandingPage.css'; // Import CSS file for styling

const LandingPage = () => {
    const navigate = useNavigate(); // Initialize useNavigate hook for programmatic navigation

    // Handler for the Login button click
    const handleLoginClick = () => {
        navigate('/login'); // Redirect to the login page
    };

    // Handler for the Register button click
    const handleRegisterClick = () => {
        navigate('/register'); // Redirect to the register page
    };

    return (
        <div className="landing-container">
            <h1>Welcome to Weather App</h1> {/* Main heading */}
            <div className="button-container">
                <button onClick={handleLoginClick}>Login</button> {/* Login button */}
                <button onClick={handleRegisterClick}>Register</button> {/* Register button */}
            </div>
        </div>
    );
};

export default LandingPage;
