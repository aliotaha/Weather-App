// src/components/PrivateRoute.js

import React from 'react';
import { Route, Navigate } from 'react-router-dom';

// Function to check if the user is authenticated
const isAuthenticated = () => !!localStorage.getItem('authToken');

// PrivateRoute component to protect routes
const PrivateRoute = ({ element, ...rest }) => {
    return isAuthenticated() ? element : <Navigate to="/login" />;
};

export default PrivateRoute;
