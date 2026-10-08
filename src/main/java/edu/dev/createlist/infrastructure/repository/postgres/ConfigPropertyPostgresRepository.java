package edu.dev.createlist.infrastructure.repository.postgres;

import edu.dev.createlist.domain.repository.ConfigPropertyRepository;
import edu.dev.createlist.infrastructure.persistence.postgres.ConfigEntityPostgres;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "POSTGRES")
public class ConfigPropertyPostgresRepository implements ConfigPropertyRepository {

    private final ConfigPropertyPostgresSpringData postgresRepo;

    public ConfigPropertyPostgresRepository(ConfigPropertyPostgresSpringData postgresRepo) {
        this.postgresRepo = postgresRepo;
    }

    @Override
    public String findByKey(String key) {
        ConfigEntityPostgres entityPostgres = postgresRepo.findByNameKey(key);
        return entityPostgres != null ? entityPostgres.getValueKey() : null;
    }
}
