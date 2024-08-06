// src/services/weatherService.js

import axiosInstance from '../axiosInstance'; // Import the configured axios instance

const API_URL = '/api/weatherdata';

// Function to get all weather data
export const fetchWeatherData = async () => {
    try {
        const response = await axiosInstance.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching weather data:', error);
        throw new Error('Failed to fetch weather data');
    }
};

// Function to get weather data by ID
export const getWeatherDataById = async (id) => {
    try {
        const response = await axiosInstance.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching weather data by ID ${id}:`, error);
        throw new Error('Failed to fetch weather data by ID');
    }
};

// Function to add new weather data
export const addWeatherData = async (weatherData) => {
    try {
        const response = await axiosInstance.post(API_URL, weatherData);
        return response.data;
    } catch (error) {
        console.error('Error adding weather data:', error);
        throw new Error('Failed to add weather data');
    }
};

// Function to update existing weather data
export const updateWeatherData = async (id, weatherData) => {
    try {
        const response = await axiosInstance.put(`${API_URL}/${id}`, weatherData);
        return response.data;
    } catch (error) {
        console.error(`Error updating weather data for ID ${id}:`, error);
        throw new Error('Failed to update weather data');
    }
};

// Function to delete weather data
export const deleteWeatherData = async (id) => {
    try {
        await axiosInstance.delete(`${API_URL}/${id}`);
    } catch (error) {
        console.error(`Error deleting weather data for ID ${id}:`, error);
        throw new Error('Failed to delete weather data');
    }
};
