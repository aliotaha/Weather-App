// src/components/charts/ChartsPage.js

import React, { useState, useEffect } from 'react';
import { fetchWeatherData } from '../../services/weatherService';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, LineElement, PointElement, Title, Tooltip, Legend } from 'chart.js';
import './ChartsPage.css';

// Register necessary components for Chart.js
ChartJS.register(
  CategoryScale, // Used for x-axis
  LinearScale, // Used for y-axis
  LineElement, // Draws the line on the chart
  PointElement, // Adds points on the line
  Title, // Adds a title to the chart
  Tooltip, // Adds tooltips for data points
  Legend // Adds a legend for dataset labels
);

const ChartsPage = () => {
    const [weatherData, setWeatherData] = useState([]); // State to hold fetched weather data
    const [showCombinedCharts, setShowCombinedCharts] = useState(false); // State to toggle between combined and separate charts

    // Function to fetch weather data
    const fetchData = async () => {
        try {
            const data = await fetchWeatherData(); // Fetch data from the service
            setWeatherData(data); // Update state with fetched data
        } catch (error) {
            console.error('Error fetching weather data:', error); // Log error if data fetching fails
        }
    };

    // useEffect hook to fetch data on component mount and set up interval for periodic updates
    useEffect(() => {
        fetchData(); // Fetch data initially

        // Set up an interval to fetch data every 30 minutes
        const intervalId = setInterval(fetchData, 1800000); // 1800000 ms = 30 minutes

        // Clean up interval on component unmount
        return () => clearInterval(intervalId);
    }, []);

    // Function to generate chart data for a single dataset
    const chartData = (label, dataKey) => ({
        labels: weatherData.map(data => new Date(data.timestamp).toLocaleString()), // X-axis labels (timestamps)
        datasets: [
            {
                label: label, // Label for the dataset
                data: weatherData.map(data => data[dataKey]), // Data points for the dataset
                borderColor: 'rgb(75, 192, 192)', // Line color
                backgroundColor: 'rgba(75, 192, 192, 0.2)', // Background color under the line
                fill: false // No fill under the line
            }
        ]
    });

    // Function to generate chart data for combined datasets
    const combinedChartData = (label1, dataKey1, label2, dataKey2) => ({
        labels: weatherData.map(data => new Date(data.timestamp).toLocaleString()), // X-axis labels (timestamps)
        datasets: [
            {
                label: label1, // Label for the first dataset
                data: weatherData.map(data => data[dataKey1]), // Data points for the first dataset
                borderColor: 'rgb(75, 192, 192)', // Line color for the first dataset
                backgroundColor: 'rgba(75, 192, 192, 0.2)', // Background color under the line for the first dataset
                fill: false // No fill under the line
            },
            {
                label: label2, // Label for the second dataset
                data: weatherData.map(data => data[dataKey2]), // Data points for the second dataset
                borderColor: 'rgb(255, 99, 132)', // Line color for the second dataset
                backgroundColor: 'rgba(255, 99, 132, 0.2)', // Background color under the line for the second dataset
                fill: false // No fill under the line
            }
        ]
    });

    return (
        <div className="charts-page-wrapper">
            <h1>Weather Charts</h1>
            {/* Toggle buttons to switch between separate and combined charts */}
            <div className="toggle-buttons">
                <button onClick={() => setShowCombinedCharts(false)}>Separate Charts</button>
                <button onClick={() => setShowCombinedCharts(true)}>Combined Charts</button>
            </div>

            {showCombinedCharts ? (
                // Render combined charts view
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
                // Render separate charts view
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
