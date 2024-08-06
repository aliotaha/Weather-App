import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import Login from './components/Login';
import Register from './components/Register';
import WeatherList from './components/weather/WeatherList';
import WeatherForm from './components/weather/WeatherForm';
import PrivateRoute from './components/PrivateRoute'; // Import PrivateRoute
import Header from './components/Header'; // Import Header

function App() {
    return (
        <Router>
            <Header /> {/* Include Header component */}
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/weather" element={<PrivateRoute element={<WeatherList />} />} />
                <Route path="/weather/add" element={<PrivateRoute element={<WeatherForm />} />} />
                <Route path="/weather/edit/:id" element={<PrivateRoute element={<WeatherForm />} />} />
            </Routes>
        </Router>
    );
}

export default App;
