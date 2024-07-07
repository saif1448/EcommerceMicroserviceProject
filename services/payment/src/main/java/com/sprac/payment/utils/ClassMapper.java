package com.sprac.payment.utils;

import com.sprac.payment.dtos.PaymentRequest;
import com.sprac.payment.models.Payment;
import org.springframework.stereotype.Service;

@Service
public class ClassMapper {
    public Payment fromPaymentRequestToPaymentEntity(PaymentRequest request) {

        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
