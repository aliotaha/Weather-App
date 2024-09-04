package com.mangodb.mangodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents weather data in the MongoDB database.
 * This class maps to the "weatherdata" collection.
 */
@Document(collection = "weatherdata")
public class WeatherData {
    
    @Id
    private String id; // Unique identifier for the weather data record

    private String city; // City for which the weather data is recorded

    private String description; // Weather description (e.g., "Clear sky")

    private double temperature; // Temperature in Celsius

    private long timestamp; // Time when the data was recorded (UNIX timestamp)

    private double pressure; // Atmospheric pressure in hPa

    private double humidity; // Humidity percentage

    private double windSpeed; // Wind speed in meters per second

    // Getters and Setters

    /**
     * Gets the unique identifier of the weather data record.
     * @return the ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the weather data record.
     * @param id the ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the city for which the weather data is recorded.
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city for which the weather data is recorded.
     * @param city the city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the weather description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the weather description.
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the temperature in Celsius.
     * @return the temperature.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature in Celsius.
     * @param temperature the temperature to set.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the timestamp of when the data was recorded.
     * @return the timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the data was recorded.
     * @param timestamp the timestamp to set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the atmospheric pressure in hPa.
     * @return the pressure.
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Sets the atmospheric pressure in hPa.
     * @param pressure the pressure to set.
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets the humidity percentage.
     * @return the humidity.
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity percentage.
     * @param humidity the humidity to set.
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets the wind speed in meters per second.
     * @return the wind speed.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the wind speed in meters per second.
     * @param windSpeed the wind speed to set.
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
