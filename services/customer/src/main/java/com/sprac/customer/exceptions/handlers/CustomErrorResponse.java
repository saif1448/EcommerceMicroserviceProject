package com.sprac.customer.exceptions.handlers;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {
}
