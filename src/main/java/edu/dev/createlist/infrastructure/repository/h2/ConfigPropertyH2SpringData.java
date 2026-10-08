package edu.dev.createlist.infrastructure.repository.h2;

import edu.dev.createlist.infrastructure.persistence.h2.ConfigPropertyEntityH2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigPropertyH2SpringData extends JpaRepository<ConfigPropertyEntityH2, String> {

    ConfigPropertyEntityH2 findByNameKey(String key);
}
