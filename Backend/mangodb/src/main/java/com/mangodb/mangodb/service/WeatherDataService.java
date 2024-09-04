package com.mangodb.mangodb.service;

import com.mangodb.mangodb.model.WeatherData;
import com.mangodb.mangodb.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for weather data operations.
 * Provides methods to handle CRUD operations for weather data.
 */
@Service
public class WeatherDataService {

    private final WeatherDataRepository weatherDataRepository;

    /**
     * Constructor to initialize WeatherDataService with WeatherDataRepository.
     *
     * @param weatherDataRepository Repository for weather data operations.
     */
    @Autowired
    public WeatherDataService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    /**
     * Retrieves all weather data from the repository.
     *
     * @return List of all WeatherData entries.
     */
    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    /**
     * Retrieves a specific weather data entry by its ID.
     *
     * @param id The ID of the weather data entry.
     * @return The WeatherData entry if found, otherwise null.
     */
    public WeatherData getWeatherDataById(String id) {
        return weatherDataRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new weather data entry to the repository.
     *
     * @param weatherData The WeatherData entry to be added.
     * @return The added WeatherData entry.
     */
    public WeatherData addWeatherData(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    /**
     * Updates an existing weather data entry by its ID.
     *
     * @param id The ID of the weather data entry to be updated.
     * @param weatherData The new WeatherData entry data.
     * @return The updated WeatherData entry if it existed, otherwise null.
     */
    public WeatherData updateWeatherData(String id, WeatherData weatherData) {
        if (weatherDataRepository.existsById(id)) {
            weatherData.setId(id);
            return weatherDataRepository.save(weatherData);
        }
        return null;
    }

    /**
     * Deletes a weather data entry by its ID.
     *
     * @param id The ID of the weather data entry to be deleted.
     */
    public void deleteWeatherData(String id) {
        weatherDataRepository.deleteById(id);
    }
}
