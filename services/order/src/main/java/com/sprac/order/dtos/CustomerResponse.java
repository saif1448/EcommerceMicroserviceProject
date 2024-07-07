package com.sprac.order.dtos;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
