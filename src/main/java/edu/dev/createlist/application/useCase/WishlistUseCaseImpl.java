package edu.dev.createlist.application.useCase;

import edu.dev.createlist.application.config.WishlistPropertiesProvider;
import edu.dev.createlist.application.dto.ProductIdsResponse;
import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.exceptions.BusinessException;
import edu.dev.createlist.domain.exceptions.NotFoundException;
import edu.dev.createlist.domain.repository.WishlistRepository;
import edu.dev.createlist.domain.vo.ProductId;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WishlistUseCaseImpl implements WishlistUseCase{

    private final WishlistRepository wishlistRepository;
    private final WishlistPropertiesProvider wishlistPropertiesProvider;

    public WishlistUseCaseImpl(WishlistRepository wishlistRepository, WishlistPropertiesProvider wishlistPropertiesProvider) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistPropertiesProvider = wishlistPropertiesProvider;
    }

    private void validateCustomerId(String customerId) {
        if(customerId == null || customerId.isBlank()) {
            throw  new IllegalArgumentException("customerId nao pode ser nulo ou branco");
        }
    }

    private void validateProductId(String productId) {
        if(productId == null || productId.isBlank()) {
            throw  new IllegalArgumentException("productId nao pode ser nulo ou branco");
        }
    }
    @Override
    public void addProduct(String customerId, String productId) {
        validateCustomerId(customerId);
        validateProductId(productId);
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElseGet(() -> new Wishlist(null, customerId, new HashSet<>()));

        // validar produto ja existe na lista
    if(wishlist.containsProduct(new ProductId(productId))) {
        throw new BusinessException("produto já existe na sua lista");
    }

        // validar tamanho maximo da lista
    if(!wishlist.canAddProduct(wishlistPropertiesProvider.getMaxProducts())) {
        throw new BusinessException("sua lista teve o limite alcançado");
    }
        wishlist.getProductIds().add(new  ProductId(productId));
        wishlistRepository.save(wishlist);
    }
    @Override
    public void removeProduct(String customerId, String productId) {
        validateCustomerId(customerId);
        validateProductId(productId);
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("lista nao encontrada"));

        if(!wishlist.getProductIds().contains(new ProductId(productId))) {
            throw new NotFoundException("produto nao encontrado na lista");
        }
        wishlist.getProductIds().remove(new ProductId(productId));
        wishlistRepository.save(wishlist);
    }
    @Override
    public void removeCustomerList(String customerId) {
        validateCustomerId(customerId);
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("lista nao encontrada"));
        wishlistRepository.deleteByCustomerId(wishlist.getCustomerId());

    }
    @Override
    public ProductIdsResponse getAllProducts(String customerId) {
        validateCustomerId(customerId);
        Optional<Wishlist> allProductsByCustomerId = wishlistRepository.findByCustomerId(customerId);

        Set<ProductId> productIds = allProductsByCustomerId
                .map(Wishlist::getProductIds)
                .orElse(Collections.emptySet());

        Set<String> idsCollect = productIds.stream()
                .map(ProductId::toString)
                .collect(Collectors.toSet());

        return new ProductIdsResponse(idsCollect);
    }


}
