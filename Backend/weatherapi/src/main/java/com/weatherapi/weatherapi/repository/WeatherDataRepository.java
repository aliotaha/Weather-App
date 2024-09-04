package com.weatherapi.weatherapi.repository;

import com.weatherapi.weatherapi.model.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing weather data stored in MongoDB.
 * This interface extends MongoRepository to provide CRUD operations for WeatherData entities.
 */
@Repository // Indicates that this interface is a Spring Data repository, which is a mechanism for data access.
public interface WeatherDataRepository extends MongoRepository<WeatherData, String> {
    // No additional methods are defined here.
    // The MongoRepository interface provides basic CRUD operations such as save, findAll, findById, and deleteById.
}
