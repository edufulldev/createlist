package edu.dev.createlist.interfaces.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ProductIdsResponseDTO {

    @JsonProperty("product_ids")
    private Set<String> productIds;


    public ProductIdsResponseDTO(Set<String> productIds) {
        this.productIds = productIds;
    }

    public Set<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<String> productIds) {
        this.productIds = productIds;
    }
}
