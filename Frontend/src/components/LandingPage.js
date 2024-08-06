import React from 'react';
import { useNavigate } from 'react-router-dom';
import './LandingPage.css'; // Import a CSS file for styling

const LandingPage = () => {
    const navigate = useNavigate(); // Initialize useNavigate hook

    const handleLoginClick = () => {
        navigate('/login'); // Redirect to login page
    };

    const handleRegisterClick = () => {
        navigate('/register'); // Redirect to register page
    };

    return (
        <div className="landing-container">
            <h1>Welcome to Weather App</h1>
            <div className="button-container">
                <button onClick={handleLoginClick}>Login</button>
                <button onClick={handleRegisterClick}>Register</button>
            </div>
        </div>
    );
};

export default LandingPage;
