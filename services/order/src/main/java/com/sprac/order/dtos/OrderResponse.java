package com.sprac.order.dtos;

import com.sprac.order.models.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    long customerId
) {
}
