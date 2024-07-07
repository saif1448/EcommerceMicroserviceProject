package com.sprac.order.dtos;

import com.sprac.order.models.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.*;

import java.math.BigDecimal;

public record OrderRequest(
        String id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be mentioned")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer cannot be empty")
        @NotEmpty(message = "Customer cannot be empty")
        @NotBlank(message = "Customer cannot be empty")
        long customerId,

        @NotEmpty(message = "You should at lease purchase one product")
        List<PurchaseRequest> products
) {
}
