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


### 3. Frontend
#### Overview
The Weather App Frontend is a React-based single-page application (SPA) that provides a user interface for interacting with the weather data and authentication services. It connects to the backend services to fetch weather data, manage user authentication, and display information to users.

#### Features
- **User Authentication:** Login, registration, and logout functionality.
- **Weather Data Management:** View, add, edit, and delete weather data.
- **Data Visualization:** Access charts to visualize weather data trends.
- **Responsive Design:** Mobile-friendly UI for optimal viewing on different devices.

### Components
#### 1. Landing Page
- **Description:** Welcome page with buttons to navigate to the login and registration pages.
- **File:** LandingPage.js, LandingPage.css
#### 2. Authentication
- **Login:** Allows users to log in to the application. On successful login, a JWT token is stored in local storage.
- **File:** Login.js, AuthStyles.css
- **Register:** Allows new users to register. On successful registration, users are prompted to log in.
- **File:** Register.js, AuthStyles.css
#### 3. Weather Management
- **Weather Form:** Form to add or update weather data. Handles data input and submission.
- **File:** WeatherForm.js, WeatherForm.css
- **Weather List:** Displays a list of weather data with options to edit or delete records. Includes a button to create new records and access charts.
- **File:** WeatherList.js, WeatherList.css
#### 4. Logout Button
- **Description:** Provides a button for users to log out. Redirects to the login page upon logout.
- **File:** LogoutButton.js, AuthStyles.css
#### 5. Private Routes
- **Description:** Protects routes by ensuring the user is authenticated before accessing certain pages.
- **File:** PrivateRoute.js
### Configuration
- **Environment Variables:** The frontend does not directly use environment variables, but ensure the backend API endpoints are correctly configured in the application's API service calls.
### Authentication Flow
- **Login:** Users log in with their username and password. A JWT token is stored in local storage upon successful authentication.
- **Registration:** Users can register with a username and password. On successful registration, they are redirected to the login page.
- **Logout:** Users can log out, which clears the JWT token from local storage and redirects to the login page.
- **Private Routes:** Routes requiring authentication are protected by checking for a valid JWT token in local storage.


