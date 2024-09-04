// src/components/LogoutButton.js

import React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for redirection
import { logout } from '../services/authService'; // Import the logout function

const LogoutButton = () => {
    const navigate = useNavigate(); // Initialize useNavigate hook for navigation

    // Function to handle logout
    const handleLogout = () => {
        logout(); // Call the logout function to handle server-side logout
        navigate('/login'); // Redirect to the login page after successful logout
    };

    // Check if the user is logged in by checking for an auth token in localStorage
    const isLoggedIn = !!localStorage.getItem('authToken'); // Use double negation to convert to boolean

    // If the user is not logged in, do not render the button
    if (!isLoggedIn) return null;

    return (
        <button onClick={handleLogout} className="logout-button">
            Logout
        </button>
    );
};

export default LogoutButton;
