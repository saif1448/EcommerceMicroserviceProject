package com.sprac.customer.models.dtos;

import com.sprac.customer.models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        long id,
        @NotNull(message = "Customer first name is required")
        String firstName,
        @NotNull(message = "Customer last name is required")
        String lastName,
        @NotNull(message = "Email cannot be empty")
        @Email(message = "Customer email is not valid")
        String email,
        Address address
) {
}
