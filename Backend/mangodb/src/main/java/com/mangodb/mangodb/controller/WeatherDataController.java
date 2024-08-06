package com.mangodb.mangodb.controller;

import com.mangodb.mangodb.model.WeatherData;
import com.mangodb.mangodb.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weatherdata")
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherDataService.getAllWeatherData();
    }

    @GetMapping("/{id}")
    public WeatherData getWeatherDataById(@PathVariable String id) {
        return weatherDataService.getWeatherDataById(id);
    }

    @PostMapping
    public WeatherData addWeatherData(@RequestBody WeatherData weatherData) {
        return weatherDataService.addWeatherData(weatherData);
    }

    @PutMapping("/{id}")
    public WeatherData updateWeatherData(@PathVariable String id, @RequestBody WeatherData weatherData) {
        return weatherDataService.updateWeatherData(id, weatherData);
    }

    @DeleteMapping("/{id}")
    public void deleteWeatherData(@PathVariable String id) {
        weatherDataService.deleteWeatherData(id);
    }
}
