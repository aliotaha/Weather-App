package com.weatherapi.weatherapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents weather data for a specific city.
 * This class is mapped to the "weatherdata" collection in MongoDB.
 */
@Document(collection = "weatherdata") // Indicates that this class is a MongoDB document and maps to the "weatherdata" collection.
public class WeatherData {

    @Id // Marks this field as the unique identifier for the MongoDB document.
    private String id;

    // The name of the city for which the weather data is recorded.
    private String city;

    // A textual description of the weather (e.g., "Clear", "Rainy").
    private String description;

    // The temperature in degrees Celsius.
    private double temperature;

    // The timestamp (in milliseconds) when the weather data was recorded.
    private long timestamp;

    // The atmospheric pressure in hPa (hectopascals).
    private double pressure;

    // The humidity level as a percentage.
    private double humidity;

    // The wind speed in meters per second.
    private double windSpeed;

    /**
     * Gets the unique identifier of the weather data.
     *
     * @return The unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the weather data.
     *
     * @param id The unique identifier to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the city name for the weather data.
     *
     * @return The city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city name for the weather data.
     *
     * @param city The city name to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the weather description.
     *
     * @return The weather description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the weather description.
     *
     * @param description The weather description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the temperature recorded.
     *
     * @return The temperature in degrees Celsius.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature recorded.
     *
     * @param temperature The temperature in degrees Celsius to set.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the timestamp when the weather data was recorded.
     *
     * @return The timestamp in milliseconds.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the weather data was recorded.
     *
     * @param timestamp The timestamp in milliseconds to set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the atmospheric pressure recorded.
     *
     * @return The pressure in hPa.
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Sets the atmospheric pressure recorded.
     *
     * @param pressure The pressure in hPa to set.
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets the humidity level recorded.
     *
     * @return The humidity level as a percentage.
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity level recorded.
     *
     * @param humidity The humidity level as a percentage to set.
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets the wind speed recorded.
     *
     * @return The wind speed in meters per second.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the wind speed recorded.
     *
     * @param windSpeed The wind speed in meters per second to set.
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
