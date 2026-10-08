package edu.dev.createlist.infrastructure.persistence;

import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.vo.ProductId;
import edu.dev.createlist.infrastructure.persistence.h2.WishlistEntityH2;
import edu.dev.createlist.infrastructure.persistence.mongo.WishlistDocumentMongo;
import edu.dev.createlist.infrastructure.persistence.postgres.WishlistEntitypostgres;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    // mongo
    WishlistDocumentMongo toDocument(Wishlist wishlist);
    Wishlist toDomain(WishlistDocumentMongo document);

    // postgres
    WishlistEntitypostgres toPostgresEntity(Wishlist wishlist);
    Wishlist toDomain(WishlistEntitypostgres entitypostgres);

    //H2
    WishlistEntityH2 toH2Entity(Wishlist wishlist);
    Wishlist toDomain(WishlistEntityH2 entity);

    // metodos auxiliares para conversao de Set<ProductId> para set<String>
    default Set<String> map(Set<ProductId> value) {
        if(value == null) return Collections.emptySet();
        return value.stream().map(ProductId::toString).collect(Collectors.toSet());
    }

    default Set<ProductId> mapToProductId(Set<String> value) {
        if(value == null) return Collections.emptySet();
        return value.stream().map(ProductId::new).collect(Collectors.toSet());
    }
}
