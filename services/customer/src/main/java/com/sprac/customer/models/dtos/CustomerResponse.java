package com.sprac.customer.models.dtos;

import com.sprac.customer.models.Address;

public record CustomerResponse(
        long id,
        String firstName,
        String lastName,
        String email,
        Address address) {
}
