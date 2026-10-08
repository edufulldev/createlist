package edu.dev.createlist.infrastructure.repository.mongo;

import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.repository.WishlistRepository;
import edu.dev.createlist.infrastructure.persistence.mongo.WishlistDocumentMongo;
import edu.dev.createlist.infrastructure.persistence.WishlistMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "wishlist.repository.type", havingValue = "mongo")
public class WishlistRepositoryMongoImpl implements WishlistRepository {

    private final WishlistMongoSpringData mongoRepo;
    private final WishlistMapper wishlistMapper;

    public WishlistRepositoryMongoImpl(WishlistMongoSpringData mongoRepo, WishlistMapper wishlistMapper) {
        this.mongoRepo = mongoRepo;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return mongoRepo.findByCustomerId(customerId)
                .map(wishlistMapper::toDomain);

    }

    @Override
    public void save(Wishlist wishlist) {
        WishlistDocumentMongo doc =wishlistMapper.toDocument(wishlist);
        mongoRepo.save(doc);
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        mongoRepo.deleteByCustomerId(customerId);
    }
}
