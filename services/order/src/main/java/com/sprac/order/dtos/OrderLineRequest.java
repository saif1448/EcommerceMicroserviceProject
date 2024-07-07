package com.sprac.order.dtos;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity) {
}
