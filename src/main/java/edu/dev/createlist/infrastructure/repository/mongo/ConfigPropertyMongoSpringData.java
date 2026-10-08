package edu.dev.createlist.infrastructure.repository.mongo;

import edu.dev.createlist.infrastructure.persistence.mongo.ConfigPropertyDocumentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigPropertyMongoSpringData extends MongoRepository<ConfigPropertyDocumentMongo,String> {
    ConfigPropertyDocumentMongo findByNameKey(String key);
}
