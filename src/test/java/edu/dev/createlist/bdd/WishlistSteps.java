package edu.dev.createlist.bdd;

import edu.dev.createlist.application.config.WishlistPropertiesProvider;
import edu.dev.createlist.application.dto.ProductIdsResponse;
import edu.dev.createlist.application.useCase.WishlistUseCaseImpl;
import edu.dev.createlist.domain.entity.Wishlist;
import edu.dev.createlist.domain.repository.WishlistRepository;
import edu.dev.createlist.domain.vo.ProductId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WishlistSteps {

    private final WishlistRepository wishlistRepository = mock(WishlistRepository.class);
    private final WishlistPropertiesProvider wishlistPropertiesProvider = mock(WishlistPropertiesProvider.class);
    private final WishlistUseCaseImpl wishlistUseCaseImpl = new WishlistUseCaseImpl(wishlistRepository, wishlistPropertiesProvider);
    private Set<ProductId> productIdSet = new HashSet<>();
    private String customerId;
    private String productId;

    @Given("wishlist is empty with id {string}")
    public void wishlist_is_empty(String id) {
        this.customerId = id;
        productIdSet.clear();
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
        assertTrue(wishlistUseCaseImpl.getAllProducts(customerId).getProductIds().isEmpty());
    }

    @When("the customer {string} adds product {string} to the wishlist")
    public void the_customer_adds_product_to_the_wishlist(String idCustomer, String idProd) {
        customerId = idCustomer;
        productId = idProd;

        Wishlist wishlist  = new Wishlist("id", customerId, productIdSet);

        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
        when(wishlistPropertiesProvider.getMaxProducts()).thenReturn(3);

        wishlistUseCaseImpl.addProduct(customerId, productId);
        productIdSet.add(new ProductId(productId));
    }

    @Then("the wishlist of customer {string} should contain product {string}")
    public void the_wishlist_should_contain_product(String idCustomer, String idProd) {
        customerId = idCustomer;
        productId = idProd;
        ProductIdsResponse allProducts = wishlistUseCaseImpl.getAllProducts(customerId);
        assertTrue(allProducts.getProductIds().contains(productId));
    }

    @When("the customer {string} removes product {string} from the wishlist")
    public void the_customer_removes_product_from_the_wishlist(String idCustomer, String idProd) {
        customerId = idCustomer;
        productId = idProd;
        wishlistUseCaseImpl.removeProduct(customerId, productId);
        productIdSet.remove(new ProductId(productId));
    }

    @Then("the wishlist of customer {string} should not contain product {string}")
    public void the_wishlist_should_not_contain_product(String idCustomer, String idProd) {
        customerId = idCustomer;
        productId = idProd;
        ProductIdsResponse allProducts = wishlistUseCaseImpl.getAllProducts(customerId);
        assertFalse(allProducts.getProductIds().contains(productId));
    }
}