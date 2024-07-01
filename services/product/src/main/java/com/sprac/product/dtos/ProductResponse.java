package com.sprac.product.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        String categoryName,
        String categoryDescription
) {
}
