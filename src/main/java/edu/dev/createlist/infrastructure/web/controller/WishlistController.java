package edu.dev.createlist.infrastructure.web.controller;

import edu.dev.createlist.application.dto.ProductIdsResponse;
import edu.dev.createlist.application.useCase.WishlistUseCase;
import edu.dev.createlist.interfaces.api.dto.ProductIdsResponseDTO;
import edu.dev.createlist.interfaces.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {


    private final WishlistUseCase wishlistUseCase;

    public WishlistController(WishlistUseCase wishlistUseCase) {
        this.wishlistUseCase = wishlistUseCase;
    }

    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable String customerId, @PathVariable String productId) {
        wishlistUseCase.addProduct(customerId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable String customerId, @PathVariable String productId) {
        wishlistUseCase.removeProduct(customerId, productId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> removeCustomerList(@PathVariable String customerId) {
        wishlistUseCase.removeCustomerList(customerId);
        return ResponseEntity.noContent().build();
    }
    // retornando todos os produtos
    @GetMapping("/{customerId}/products")
    public ResponseEntity<ResponseDTO<ProductIdsResponseDTO>> getAllProducts(@PathVariable String customerId) {

        ProductIdsResponse productIdsResponse = wishlistUseCase.getAllProducts(customerId);
        ProductIdsResponseDTO productIdsResponseDTO = new ProductIdsResponseDTO(productIdsResponse.getProductIds());
        ResponseDTO<ProductIdsResponseDTO> responseDTO =
                new ResponseDTO<>(productIdsResponseDTO, "Sucesso", HttpStatus.OK.value());
        return ResponseEntity.ok(responseDTO);
    }
}
