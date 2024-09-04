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

/**
 * Service class responsible for fetching weather data from an external API
 * and saving it to the MongoDB repository.
 * This class is scheduled to run periodically to update the weather data.
 */
@Service // Indicates that this class is a Spring service component.
public class WeatherService {

    @Value("${openweathermap.api.key}") // Injects the API key for OpenWeatherMap from application properties.
    private String apiKey;

    @Autowired // Injects the WeatherDataRepository bean into this service.
    private WeatherDataRepository weatherDataRepository;

    private final RestTemplate restTemplate; // RestTemplate used to make HTTP requests to the weather API.
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class); // Logger for logging messages.

    /**
     * Constructor that initializes RestTemplate with custom timeouts.
     *
     * @param builder RestTemplateBuilder used to configure the RestTemplate instance.
     */
    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(5)) // Sets the connection timeout to 5 seconds.
                .setReadTimeout(Duration.ofSeconds(5)) // Sets the read timeout to 5 seconds.
                .build(); // Builds and initializes the RestTemplate instance.
    }

    /**
     * Scheduled method that fetches weather data from the OpenWeatherMap API
     * and saves the data to the MongoDB repository.
     * This method is executed every 30 minutes.
     */
    @Scheduled(fixedRate = 1800000) // Schedules this method to run every 30 minutes (1800000 milliseconds).
    public void fetchWeatherData() {
        // Constructs the URL for the weather API request with the injected API key.
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=53.423931&lon=-7.940690&appid=" + apiKey;

        try {
            // Makes the HTTP GET request to the weather API and maps the response to WeatherApiResponse class.
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response != null) {
                // Creates a new WeatherData object and populates it with data from the API response.
                WeatherData weatherData = new WeatherData();
                weatherData.setCity(response.getName()); // Sets the city name.
                weatherData.setDescription(response.getWeather().get(0).getDescription()); // Sets the weather description.

                // Converts temperature from Kelvin to Celsius and sets it.
                double tempCelsius = response.getMain().getTemp() - 273.15;
                weatherData.setTemperature(tempCelsius);

                weatherData.setPressure(response.getMain().getPressure()); // Sets the atmospheric pressure.
                weatherData.setHumidity(response.getMain().getHumidity()); // Sets the humidity level.
                weatherData.setWindSpeed(response.getWind().getSpeed()); // Sets the wind speed.
                weatherData.setTimestamp(System.currentTimeMillis()); // Sets the current timestamp.

                // Saves the populated WeatherData object to the MongoDB repository.
                weatherDataRepository.save(weatherData);

                // Logs the successful fetch and save operation.
                logger.info("Weather data fetched and saved: {}", weatherData);
            } else {
                // Logs an error if the API response is null.
                logger.error("Received null response from weather API");
            }
        } catch (Exception e) {
            // Logs any exception that occurs during the fetch operation.
            logger.error("Error fetching weather data", e);
        }
    }
}
