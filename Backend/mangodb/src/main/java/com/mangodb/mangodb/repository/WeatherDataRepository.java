package com.mangodb.mangodb.repository;


import com.mangodb.mangodb.model.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDataRepository extends MongoRepository<WeatherData, String> {
}
