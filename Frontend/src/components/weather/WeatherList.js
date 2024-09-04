// src/components/weather/WeatherList.js

import React, { useEffect, useState } from 'react';
import { fetchWeatherData, deleteWeatherData } from '../../services/weatherService';
import { useNavigate } from 'react-router-dom';
import './WeatherList.css'; // Import the CSS file for styling

const WeatherList = () => {
    // State to store weather data, loading state, and error message
    const [weatherData, setWeatherData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const navigate = useNavigate(); // Hook for navigation

    // Fetch weather data on component mount
    useEffect(() => {
        const getWeatherData = async () => {
            try {
                const data = await fetchWeatherData(); // Fetch weather data from the API
                setWeatherData(data); // Update state with fetched data
                setLoading(false); // Data fetched, set loading to false
            } catch (err) {
                setError('Failed to fetch weather data.'); // Set error message on failure
                setLoading(false); // Data fetching failed, set loading to false
            }
        };

        getWeatherData(); // Call function to fetch data
    }, []); // Empty dependency array means this runs once on mount

    // Function to handle deletion of a weather record
    const handleDelete = async (id) => {
        try {
            await deleteWeatherData(id); // Call API to delete record
            setWeatherData(weatherData.filter(item => item.id !== id)); // Update state to remove deleted item
        } catch (err) {
            setError('Failed to delete weather data.'); // Set error message on failure
        }
    };

    // Navigate to create a new weather record
    const handleCreateNew = () => {
        navigate('/weather/add');
    };

    // Navigate to the charts page
    const handleCharts = () => {
        navigate('/weather/charts');
    };

    // Handle user logout
    const handleLogout = () => {
        localStorage.removeItem('authToken'); // Remove authentication token
        window.location.href = '/login'; // Redirect to login page
    };

    // Display loading message while data is being fetched
    if (loading) return <p>Loading...</p>;
    // Display error message if there is an error
    if (error) return <p className="error-message">{error}</p>;

    return (
        <div className="weather-list-wrapper">
            <div className="weather-list-container">
                <h2>Weather Data</h2>
                {/* Action buttons */}
                <div className="action-buttons">
                    <button onClick={handleCreateNew}>Create New Record</button>
                    <button onClick={handleCharts}>Charts</button>
                </div>
                {/* Table to display weather data */}
                <table className="weather-table">
                    <thead>
                        <tr>
                            <th>City</th>
                            <th>Description</th>
                            <th>Temperature (°C)</th>
                            <th>Pressure (hPa)</th>
                            <th>Humidity (%)</th>
                            <th>Wind Speed (m/s)</th>
                            <th>Timestamp</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {/* Map through weather data to generate table rows */}
                        {weatherData.map((item) => (
                            <tr key={item.id}>
                                <td>{item.city}</td>
                                <td>{item.description}</td>
                                <td>{item.temperature.toFixed(2)}</td>
                                <td>{item.pressure.toFixed(1)}</td>
                                <td>{item.humidity.toFixed(1)}</td>
                                <td>{item.windSpeed.toFixed(1)}</td>
                                <td>{new Date(item.timestamp).toLocaleString()}</td>
                                <td>
                                    {/* Edit and delete buttons */}
                                    <button className="edit-button" onClick={() => navigate(`/weather/edit/${item.id}`)}>Edit</button>
                                    <button className="delete-button" onClick={() => handleDelete(item.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {/* Logout button */}
            <button className="logout-button" onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default WeatherList;
