# Weather App
## BackEnd
### 1. Weatherapi
#### Overview
A Spring Boot service that fetches weather data from OpenWeatherMap and stores it in MongoDB.

#### Features
Fetches weather data every 30 minutes.
Stores data in MongoDB.
#### Components
- **Application Entry:** WeatherapiApplication - Starts the Spring Boot application.
- **Configuration:** AppConfig - Configures RestTemplate with timeouts.
- **Data Model:** WeatherData - Defines the structure for weather data.
- **Data Access:** WeatherDataRepository - Handles CRUD operations in MongoDB.
- **Service:** WeatherService - Fetches and saves weather data.
- **API Response:** WeatherApiResponse - Maps OpenWeatherMap API response.
#### Configuration
##### application.properties:
- `spring.application.name`: Application name.
- `openweathermap.api.key`: API key for OpenWeatherMap.
- `logging.level.com.weatherapi.weatherapi.service.WeatherService`: Logging level.
- `spring.data.mongodb.uri`: MongoDB connection URI.
 
