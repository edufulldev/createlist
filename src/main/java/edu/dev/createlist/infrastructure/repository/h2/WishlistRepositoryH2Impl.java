package edu.dev.createlist.infrastructure.repository.h2;

import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.repository.WishlistRepository;
import edu.dev.createlist.infrastructure.persistence.WishlistMapper;
import edu.dev.createlist.infrastructure.persistence.h2.WishlistEntityH2;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "H2")
public class WishlistRepositoryH2Impl implements WishlistRepository {

    private final WishlistH2SpringData h2Repo;
    private final WishlistMapper wishlistMapper;

    public WishlistRepositoryH2Impl(WishlistH2SpringData h2Repo, WishlistMapper wishlistMapper) {
        this.h2Repo = h2Repo;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return h2Repo.findByCustomerId(customerId)
                .map(wishlistMapper::toDomain);
    }

    @Override
    public void save(Wishlist wishlist) {
        WishlistEntityH2 entity = wishlistMapper.toH2Entity(wishlist);
        h2Repo.save(entity);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(String customerId) {
        h2Repo.deleteByCustomerId(customerId);
    }
}
