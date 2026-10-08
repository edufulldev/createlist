package edu.dev.createlist.infrastructure.repository.h2;

import edu.dev.createlist.domain.repository.ConfigPropertyRepository;
import edu.dev.createlist.infrastructure.persistence.h2.ConfigPropertyEntityH2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "H2")
public class ConfigPropertyH2Repository implements ConfigPropertyRepository  {

    private final ConfigPropertyH2SpringData h2Repo;

    public ConfigPropertyH2Repository(ConfigPropertyH2SpringData h2Repo) {
        this.h2Repo = h2Repo;
    }

    @Override
    public String findByKey(String key) {
        ConfigPropertyEntityH2 entity = h2Repo.findByNameKey(key);
        return entity != null ? entity.getValueKey() : null;
    }
}
