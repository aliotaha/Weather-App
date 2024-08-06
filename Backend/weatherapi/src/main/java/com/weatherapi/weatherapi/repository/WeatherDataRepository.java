package com.weatherapi.weatherapi.repository;

import com.weatherapi.weatherapi.model.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends MongoRepository<WeatherData, String> {
}
