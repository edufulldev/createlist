package edu.dev.createlist.infrastructure.repository.postgres;

import edu.dev.createlist.infrastructure.persistence.postgres.ConfigEntityPostgres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigPropertyPostgresSpringData extends JpaRepository<ConfigEntityPostgres, String> {

    ConfigEntityPostgres findByNameKey(String key);
}
