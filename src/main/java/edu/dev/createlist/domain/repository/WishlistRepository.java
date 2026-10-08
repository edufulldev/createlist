package edu.dev.createlist.domain.repository;

import edu.dev.createlist.domain.entity.Wishlist;

import java.util.Optional;

public interface WishlistRepository {

    Optional<Wishlist> findByCustomerId(String customerId);

    void save(Wishlist wishlist);

    void deleteByCustomerId(String customerId);
}
