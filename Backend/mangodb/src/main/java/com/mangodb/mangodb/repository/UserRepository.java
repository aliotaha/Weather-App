package com.mangodb.mangodb.repository;

import com.mangodb.mangodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities in MongoDB.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds a user by their username.
     * @param username the username to search for.
     * @return an Optional containing the found user, or empty if no user with the given username exists.
     */
    Optional<User> findByUsername(String username);
}
