package com.sprac.notification.kafka.order;

import com.sprac.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.*;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
