package com.weatherapi.weatherapi.service;

import java.util.List;

/**
 * Represents the response structure from the OpenWeatherMap API.
 * This class maps the JSON response from the API into Java objects.
 */
public class WeatherApiResponse {

    // Coordinates of the location (longitude and latitude).
    private Coord coord;

    // List of weather conditions at the location.
    private List<Weather> weather;

    // Main weather data including temperature, pressure, and humidity.
    private Main main;

    // Wind conditions including speed and direction.
    private Wind wind;

    // Cloudiness percentage.
    private Clouds clouds;

    // System information including country, sunrise, and sunset times.
    private Sys sys;

    // Name of the location (e.g., city name).
    private String name;

    // Getters and setters for each field.

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Represents the coordinates of the location.
     */
    public static class Coord {
        private double lon; // Longitude of the location.
        private double lat; // Latitude of the location.

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    /**
     * Represents weather conditions at the location.
     */
    public static class Weather {
        private int id; // Weather condition ID.
        private String main; // Group of weather parameters (e.g., "Rain").
        private String description; // Weather condition description (e.g., "light rain").
        private String icon; // Weather icon identifier.

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    /**
     * Represents main weather data including temperature, pressure, and humidity.
     */
    public static class Main {
        private double temp; // Temperature in Kelvin.
        private double feels_like; // Feels-like temperature in Kelvin.
        private double temp_min; // Minimum temperature at the location in Kelvin.
        private double temp_max; // Maximum temperature at the location in Kelvin.
        private double pressure; // Atmospheric pressure in hPa.
        private double humidity; // Humidity percentage.
        private double sea_level; // Atmospheric pressure at sea level in hPa.
        private double grnd_level; // Atmospheric pressure at ground level in hPa.

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getSea_level() {
            return sea_level;
        }

        public void setSea_level(double sea_level) {
            this.sea_level = sea_level;
        }

        public double getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(double grnd_level) {
            this.grnd_level = grnd_level;
        }
    }

    /**
     * Represents wind conditions at the location.
     */
    public static class Wind {
        private double speed; // Wind speed in meters per second.
        private double deg; // Wind direction in degrees.
        private double gust; // Wind gust speed in meters per second.

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }

        public double getGust() {
            return gust;
        }

        public void setGust(double gust) {
            this.gust = gust;
        }
    }

    /**
     * Represents cloudiness information.
     */
    public static class Clouds {
        private int all; // Cloudiness percentage.

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    /**
     * Represents system information including country, sunrise, and sunset times.
     */
    public static class Sys {
        private String country; // Country code (e.g., "US").
        private long sunrise; // Sunrise time in Unix timestamp (seconds since epoch).
        private long sunset; // Sunset time in Unix timestamp (seconds since epoch).

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }
}
