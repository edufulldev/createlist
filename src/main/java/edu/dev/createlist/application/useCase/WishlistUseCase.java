package edu.dev.createlist.application.useCase;

import edu.dev.createlist.application.dto.ProductIdsResponse;

public interface WishlistUseCase {

    void addProduct(String customerId, String productId);
    void removeProduct(String customerId, String productId);
    void removeCustomerList(String customerId);
    ProductIdsResponse getAllProducts(String customerId);
}
