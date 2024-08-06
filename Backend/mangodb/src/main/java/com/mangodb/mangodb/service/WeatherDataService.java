package com.mangodb.mangodb.service;

import com.mangodb.mangodb.model.WeatherData;
import com.mangodb.mangodb.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;

    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    public WeatherData getWeatherDataById(String id) {
        return weatherDataRepository.findById(id).orElse(null);
    }

    public WeatherData addWeatherData(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    public WeatherData updateWeatherData(String id, WeatherData weatherData) {
        if (weatherDataRepository.existsById(id)) {
            weatherData.setId(id);
            return weatherDataRepository.save(weatherData);
        }
        return null;
    }

    public void deleteWeatherData(String id) {
        weatherDataRepository.deleteById(id);
    }
}
