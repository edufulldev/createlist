package edu.dev.createlist.infrastructure.repository.h2;

import edu.dev.createlist.infrastructure.persistence.h2.WishlistEntityH2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistH2SpringData extends JpaRepository<WishlistEntityH2, Long> {

    Optional<WishlistEntityH2> findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
}
