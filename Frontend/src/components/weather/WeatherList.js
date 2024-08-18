import React, { useEffect, useState } from 'react';
import { fetchWeatherData, deleteWeatherData } from '../../services/weatherService';
import { useNavigate } from 'react-router-dom';
import './WeatherList.css'; // Import the CSS file for styling

const WeatherList = () => {
    const [weatherData, setWeatherData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const getWeatherData = async () => {
            try {
                const data = await fetchWeatherData();
                setWeatherData(data);
                setLoading(false);
            } catch (err) {
                setError('Failed to fetch weather data.');
                setLoading(false);
            }
        };

        getWeatherData();
    }, []);

    const handleDelete = async (id) => {
        try {
            await deleteWeatherData(id);
            setWeatherData(weatherData.filter(item => item.id !== id));
        } catch (err) {
            setError('Failed to delete weather data.');
        }
    };

    const handleCreateNew = () => {
        navigate('/weather/add');
    };

    const handleCharts = () => {
        navigate('/weather/charts');
    };

    const handleLogout = () => {
        // Clear token and redirect to login page
        localStorage.removeItem('authToken');
        window.location.href = '/login';
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p className="error-message">{error}</p>;

    return (
        <div className="weather-list-wrapper">
            <div className="weather-list-container">
                <h2>Weather Data</h2>
                <div className="action-buttons">
                    <button onClick={handleCreateNew}>Create New Record</button>
                    <button onClick={handleCharts}>Charts</button>
                </div>
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
                                    <button className="edit-button" onClick={() => navigate(`/weather/edit/${item.id}`)}>Edit</button>
                                    <button className="delete-button" onClick={() => handleDelete(item.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <button className="logout-button" onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default WeatherList;
