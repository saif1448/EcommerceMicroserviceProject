package com.sprac.product.dtos;

import com.sprac.product.models.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        @NotNull(message = "Product name is required")
        String name,

        String description,

        @NotNull(message = "Product Quantity cannot is required")
        @Positive(message = "Available quantity should be a positive number")
        double availableQuantity,

        @Positive(message = "Price should be a positive number")
        BigDecimal price,

        @NotNull(message = "Product category id is required")
        Integer categoryId
) {

}
