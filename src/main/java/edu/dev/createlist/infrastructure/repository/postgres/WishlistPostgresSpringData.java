package edu.dev.createlist.infrastructure.repository.postgres;

import edu.dev.createlist.infrastructure.persistence.postgres.WishlistEntitypostgres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistPostgresSpringData extends JpaRepository<WishlistEntitypostgres, Long> {

    Optional<WishlistEntitypostgres> findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
}
