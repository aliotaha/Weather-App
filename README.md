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


### 2. mangodb Application
#### Overview
The mangodb application is a Spring Boot-based service that interacts with MongoDB to manage weather data and user authentication. It provides endpoints for CRUD operations on weather data and user registration and authentication using JWT.

#### Features
##### Weather Data Management:
 **CRUD Operations:** Create, Read, Update, and Delete weather data.
 **Endpoints:**
- **GET /api/weatherdata:** Retrieve all weather data.
- **GET /api/weatherdata/{id}:** Retrieve weather data by ID.
- **POST /api/weatherdata:** Add new weather data.
- **PUT /api/weatherdata/{id}:** Update existing weather data.
- **DELETE /api/weatherdata/{id}:** Delete weather data by ID.
###### User Authentication:
 **Registration and Login:** Register new users and authenticate existing ones.
 **JWT-based Security:**
- Login Endpoint: POST /auth/login
- Registration Endpoint: POST /auth/register
 **JWT Token:** Secure the API with JWT tokens for authenticated requests.

### Configuration
 **Application Name:** mangodb
 **Database Configuration:**
- MongoDB Host: mongodb
- MongoDB Port: 27017
- Database Name: weatherdata
**Server Configuration:**
     Port: 9090
     
### Security
**JWT Authentication:**
    Uses JWT tokens for securing endpoints.
    Tokens are signed using a secret key and have a validity period of 5 hours.
    Includes filters and entry points for managing token authentication and handling unauthorized access.


