// src/components/charts/ChartsPage.js
import React, { useState, useEffect } from 'react';
import { fetchWeatherData } from '../../services/weatherService';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, LineElement, PointElement, Title, Tooltip, Legend } from 'chart.js';
import './ChartsPage.css'; // Import the CSS file for styling

// Register necessary components
ChartJS.register(
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  Title,
  Tooltip,
  Legend
);

const ChartsPage = () => {
    const [weatherData, setWeatherData] = useState([]);

    const fetchData = async () => {
        try {
            const data = await fetchWeatherData();
            setWeatherData(data);
        } catch (error) {
            console.error('Error fetching weather data:', error);
        }
    };

    useEffect(() => {
        fetchData(); // Fetch data on mount

        const intervalId = setInterval(fetchData, 1800000); // Fetch data every 30 minutes

        return () => clearInterval(intervalId); // Cleanup interval on unmount
    }, []);

    const chartData = (label, dataKey) => ({
        labels: weatherData.map(data => new Date(data.timestamp).toLocaleString()),
        datasets: [
            {
                label: label,
                data: weatherData.map(data => data[dataKey]),
                borderColor: 'rgb(75, 192, 192)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                fill: false
            }
        ]
    });

    return (
        <div className="charts-page-wrapper">
            <h1>Weather Charts</h1>
            <div className="charts-grid">
                <div className="chart-item">
                    <h2>Temperature (°C)</h2>
                    <Line data={chartData('Temperature (°C)', 'temperature')} options={{ responsive: true }} />
                </div>
                <div className="chart-item">
                    <h2>Pressure (hPa)</h2>
                    <Line data={chartData('Pressure (hPa)', 'pressure')} options={{ responsive: true }} />
                </div>
                <div className="chart-item">
                    <h2>Humidity (%)</h2>
                    <Line data={chartData('Humidity (%)', 'humidity')} options={{ responsive: true }} />
                </div>
                <div className="chart-item">
                    <h2>Wind Speed (m/s)</h2>
                    <Line data={chartData('Wind Speed (m/s)', 'windSpeed')} options={{ responsive: true }} />
                </div>
            </div>
        </div>
    );
};

export default ChartsPage;
