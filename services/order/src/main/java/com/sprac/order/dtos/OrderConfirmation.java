package com.sprac.order.dtos;

import com.sprac.order.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.*;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
