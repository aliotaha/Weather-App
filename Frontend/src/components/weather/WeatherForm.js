// src/components/weather/WeatherForm.js

import React, { useState, useEffect } from 'react';
import { addWeatherData, updateWeatherData, getWeatherDataById } from '../../services/weatherService';
import { useParams, useNavigate } from 'react-router-dom';

const WeatherForm = () => {
    const { id } = useParams(); // Get the ID from URL params for update operations
    const navigate = useNavigate(); // Hook to navigate programmatically

    // State variables to hold form data
    const [city, setCity] = useState('');
    const [description, setDescription] = useState('');
    const [temperature, setTemperature] = useState('');
    const [pressure, setPressure] = useState('');
    const [humidity, setHumidity] = useState('');
    const [windSpeed, setWindSpeed] = useState('');
    const [timestamp, setTimestamp] = useState('');
    const [error, setError] = useState(''); // State for error messages

    useEffect(() => {
        if (id) {
            // Fetch existing weather data for update operation
            const fetchWeatherData = async () => {
                try {
                    const data = await getWeatherDataById(id); // Fetch data by ID
                    setCity(data.city || '');
                    setDescription(data.description || '');
                    setTemperature(data.temperature || '');
                    setPressure(data.pressure || '');
                    setHumidity(data.humidity || '');
                    setWindSpeed(data.windSpeed || '');
                    // Convert UTC timestamp to local datetime string for input
                    const localDate = new Date(data.timestamp);
                    // Convert to local datetime string (YYYY-MM-DDTHH:MM)
                    const localDateString = localDate.toISOString().slice(0, 16);
                    setTimestamp(localDateString);
                } catch (error) {
                    setError('Failed to fetch weather data'); // Set error if fetching fails
                }
            };
            fetchWeatherData();
        }
    }, [id]);

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        // Convert local datetime string to UTC timestamp in milliseconds
        const localDate = new Date(timestamp);
        const utcTimestamp = localDate.getTime();

        // Create a weather data object
        const weatherData = {
            city,
            description,
            temperature: parseFloat(temperature),
            pressure: parseFloat(pressure),
            humidity: parseFloat(humidity),
            windSpeed: parseFloat(windSpeed),
            timestamp: utcTimestamp // Send UTC timestamp to the server
        };

        try {
            if (id) {
                await updateWeatherData(id, weatherData); // Update existing data
            } else {
                await addWeatherData(weatherData); // Add new data
            }
            navigate('/weather'); // Redirect after saving
        } catch (error) {
            setError('Failed to save weather data'); // Set error if saving fails
        }
    };

    return (
        <div>
            <h2>{id ? 'Update' : 'Add'} Weather Data</h2>
            {/* Form for adding or updating weather data */}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>City</label>
                    <input
                        type="text"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Description</label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Temperature (°C)</label>
                    <input
                        type="number"
                        value={temperature}
                        onChange={(e) => setTemperature(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Pressure (hPa)</label>
                    <input
                        type="number"
                        value={pressure}
                        onChange={(e) => setPressure(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Humidity (%)</label>
                    <input
                        type="number"
                        value={humidity}
                        onChange={(e) => setHumidity(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Wind Speed (m/s)</label>
                    <input
                        type="number"
                        value={windSpeed}
                        onChange={(e) => setWindSpeed(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Timestamp</label>
                    <input
                        type="datetime-local"
                        value={timestamp}
                        onChange={(e) => setTimestamp(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">{id ? 'Update' : 'Add'} Weather Data</button>
            </form>
            {error && <p>{error}</p>} {/* Display error message if present */}
        </div>
    );
};

export default WeatherForm;
