// src/components/charts/ChartsPage.js

import React, { useState, useEffect } from 'react';
import { fetchWeatherData } from '../../services/weatherService';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, LineElement, PointElement, Title, Tooltip, Legend } from 'chart.js';
import './ChartsPage.css';

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
    const [showCombinedCharts, setShowCombinedCharts] = useState(false); // State for toggling views

    const fetchData = async () => {
        try {
            const data = await fetchWeatherData();
            setWeatherData(data);
        } catch (error) {
            console.error('Error fetching weather data:', error);
        }
    };

    useEffect(() => {
        fetchData();

        const intervalId = setInterval(fetchData, 1800000);

        return () => clearInterval(intervalId);
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

    const combinedChartData = (label1, dataKey1, label2, dataKey2) => ({
        labels: weatherData.map(data => new Date(data.timestamp).toLocaleString()),
        datasets: [
            {
                label: label1,
                data: weatherData.map(data => data[dataKey1]),
                borderColor: 'rgb(75, 192, 192)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                fill: false
            },
            {
                label: label2,
                data: weatherData.map(data => data[dataKey2]),
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                fill: false
            }
        ]
    });

    return (
        <div className="charts-page-wrapper">
            <h1>Weather Charts</h1>
            {/* Toggle buttons */}
            <div className="toggle-buttons">
                <button onClick={() => setShowCombinedCharts(false)}>Separate Charts</button>
                <button onClick={() => setShowCombinedCharts(true)}>Combined Charts</button>
            </div>

            {showCombinedCharts ? (
                // Combined Charts View
                <div className="charts-grid">
                    <div className="chart-item">
                        <h2>Temperature (°C) and Humidity (%)</h2>
                        <Line data={combinedChartData('Temperature (°C)', 'temperature', 'Humidity (%)', 'humidity')} options={{ responsive: true }} />
                    </div>
                    <div className="chart-item">
                        <h2>Pressure (hPa) and Wind Speed (m/s)</h2>
                        <Line data={combinedChartData('Pressure (hPa)', 'pressure', 'Wind Speed (m/s)', 'windSpeed')} options={{ responsive: true }} />
                    </div>
                </div>
            ) : (
                // Separate Charts View
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
            )}
        </div>
    );
};

export default ChartsPage;
