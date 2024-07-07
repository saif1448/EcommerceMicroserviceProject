package com.sprac.order.utils;

import com.sprac.order.dtos.OrderLineRequest;
import com.sprac.order.dtos.OrderLineResponse;
import com.sprac.order.dtos.OrderRequest;
import com.sprac.order.dtos.OrderResponse;
import com.sprac.order.models.Order;
import com.sprac.order.models.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class ClassMapper {

    public Order orderRequesrtToOrderEntityMapper(OrderRequest request) {
        return Order.builder()
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderLine orderLineRequestToOrderLineEntityMapper(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .productId(request.productId())
                .order(Order.builder()
                        .id(request.orderId())
                        .build())
                .quantity(request.quantity())
                .build();
    }

    public OrderResponse fromOrderEntityToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

    public OrderLineResponse fromOrderEntityToOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
