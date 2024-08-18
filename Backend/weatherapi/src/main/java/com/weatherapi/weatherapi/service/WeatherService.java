package com.weatherapi.weatherapi.service;

import com.weatherapi.weatherapi.model.WeatherData;
import com.weatherapi.weatherapi.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Scheduled(fixedRate = 1800000)
    public void fetchWeatherData() {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=53.423931&lon=-7.940690&appid=" + apiKey;

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response != null) {
                WeatherData weatherData = new WeatherData();
                weatherData.setCity(response.getName());
                weatherData.setDescription(response.getWeather().get(0).getDescription());

           
                double tempCelsius = response.getMain().getTemp() - 273.15;
                weatherData.setTemperature(tempCelsius);

                weatherData.setPressure(response.getMain().getPressure());
                weatherData.setHumidity(response.getMain().getHumidity());
                weatherData.setWindSpeed(response.getWind().getSpeed());
                weatherData.setTimestamp(System.currentTimeMillis());

                weatherDataRepository.save(weatherData);

                logger.info("Weather data fetched and saved: {}", weatherData);
            } else {
                logger.error("Received null response from weather API");
            }
        } catch (Exception e) {
            logger.error("Error fetching weather data", e);
        }
    }
}
