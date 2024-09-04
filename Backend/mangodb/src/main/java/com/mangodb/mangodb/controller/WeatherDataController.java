package com.mangodb.mangodb.controller;

import com.mangodb.mangodb.model.WeatherData;
import com.mangodb.mangodb.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing weather data.
 * Provides endpoints to perform CRUD operations on weather data.
 */
@RestController
@RequestMapping("/api/weatherdata")
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * Retrieves all weather data.
     * @return List of WeatherData objects.
     */
    @GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherDataService.getAllWeatherData();
    }

    /**
     * Retrieves a specific weather data entry by its ID.
     * @param id The ID of the weather data entry.
     * @return WeatherData object with the specified ID.
     */
    @GetMapping("/{id}")
    public WeatherData getWeatherDataById(@PathVariable String id) {
        return weatherDataService.getWeatherDataById(id);
    }

    /**
     * Adds a new weather data entry.
     * @param weatherData The WeatherData object to be added.
     * @return The added WeatherData object.
     */
    @PostMapping
    public WeatherData addWeatherData(@RequestBody WeatherData weatherData) {
        return weatherDataService.addWeatherData(weatherData);
    }

    /**
     * Updates an existing weather data entry by its ID.
     * @param id The ID of the weather data entry to be updated.
     * @param weatherData The updated WeatherData object.
     * @return The updated WeatherData object.
     */
    @PutMapping("/{id}")
    public WeatherData updateWeatherData(@PathVariable String id, @RequestBody WeatherData weatherData) {
        return weatherDataService.updateWeatherData(id, weatherData);
    }

    /**
     * Deletes a weather data entry by its ID.
     * @param id The ID of the weather data entry to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteWeatherData(@PathVariable String id) {
        weatherDataService.deleteWeatherData(id);
    }
}
