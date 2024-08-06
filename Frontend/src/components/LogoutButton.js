// src/components/LogoutButton.js

import React from 'react';
import { useNavigate } from 'react-router-dom';
import { logout } from '../services/authService'; // Import the logout function

const LogoutButton = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login'); // Redirect to login page after logout
    };

    // Check if user is logged in
    const isLoggedIn = !!localStorage.getItem('authToken'); // Adjust based on your token storage

    if (!isLoggedIn) return null; // Do not render if not logged in

    return (
        <button onClick={handleLogout} className="logout-button">
            Logout
        </button>
    );
};

export default LogoutButton;
