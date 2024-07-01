package com.sprac.product.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(

        @NotNull(message = "Product ID is required")
        Integer productId,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity amount should be positive")
        double quantity
) {
}
