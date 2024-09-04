package com.mangodb.mangodb.repository;

import com.mangodb.mangodb.model.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link WeatherData} entities in MongoDB.
 */
public interface WeatherDataRepository extends MongoRepository<WeatherData, String> {
    // This interface extends MongoRepository to provide standard CRUD operations
    // for the WeatherData entity. No additional methods are defined here.
}
