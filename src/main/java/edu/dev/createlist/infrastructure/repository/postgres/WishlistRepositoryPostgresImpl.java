package edu.dev.createlist.infrastructure.repository.postgres;

import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.repository.WishlistRepository;
import edu.dev.createlist.infrastructure.persistence.WishlistMapper;
import edu.dev.createlist.infrastructure.persistence.postgres.WishlistEntitypostgres;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "POSTGRES")
public class WishlistRepositoryPostgresImpl implements WishlistRepository {

    private final WishlistPostgresSpringData postgresRepo;
    private final WishlistMapper wishlistMapper;

    public WishlistRepositoryPostgresImpl(WishlistPostgresSpringData postgresRepo, WishlistMapper wishlistMapper) {
        this.postgresRepo = postgresRepo;
        this.wishlistMapper = wishlistMapper;
    }


    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return postgresRepo.findByCustomerId(customerId)
                .map(wishlistMapper::toDomain);
    }

    @Override
    public void save(Wishlist wishlist) {
        WishlistEntitypostgres entitypostgres  = wishlistMapper.toPostgresEntity(wishlist);
        postgresRepo.save(entitypostgres);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(String customerId) {
        postgresRepo.deleteByCustomerId(customerId);
    }
}
