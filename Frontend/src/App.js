// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import Login from './components/Login';
import Register from './components/Register';
import WeatherList from './components/weather/WeatherList';
import WeatherForm from './components/weather/WeatherForm';
import ChartsPage from './components/charts/ChartsPage'; // Import ChartsPage
import PrivateRoute from './components/PrivateRoute';
import Header from './components/Header';

function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/weather" element={<PrivateRoute element={<WeatherList />} />} />
                <Route path="/weather/add" element={<PrivateRoute element={<WeatherForm />} />} />
                <Route path="/weather/edit/:id" element={<PrivateRoute element={<WeatherForm />} />} />
                <Route path="/weather/charts" element={<PrivateRoute element={<ChartsPage />} />} /> {/* New Route */}
            </Routes>
        </Router>
    );
}

export default App;
