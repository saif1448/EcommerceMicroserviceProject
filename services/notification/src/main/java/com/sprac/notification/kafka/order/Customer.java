package com.sprac.notification.kafka.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record Customer(
        long id,
        String firstName,
        String lastName,
        String email
) {
}
