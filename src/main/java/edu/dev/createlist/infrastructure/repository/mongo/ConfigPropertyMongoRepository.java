package edu.dev.createlist.infrastructure.repository.mongo;

import edu.dev.createlist.domain.repository.ConfigPropertyRepository;
import edu.dev.createlist.infrastructure.persistence.mongo.ConfigPropertyDocumentMongo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "mongo")
public class ConfigPropertyMongoRepository implements ConfigPropertyRepository {

    private final ConfigPropertyMongoSpringData mongoRepo;

    public ConfigPropertyMongoRepository(ConfigPropertyMongoSpringData mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public String findByKey(String key) {
        ConfigPropertyDocumentMongo doc = mongoRepo.findByNameKey(key);

        return doc != null ? doc.getValueKey() : null;
    }
}
