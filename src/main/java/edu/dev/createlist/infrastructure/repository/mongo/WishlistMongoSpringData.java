package edu.dev.createlist.infrastructure.repository.mongo;

import edu.dev.createlist.infrastructure.persistence.mongo.WishlistDocumentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistMongoSpringData extends MongoRepository<WishlistDocumentMongo, String> {

    Optional<WishlistDocumentMongo> findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);
}
