package com.sprac.order.exceptions.handlers;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {
}
