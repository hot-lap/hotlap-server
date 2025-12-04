package com.hotlap.server.inbound.controller.health.model.response;

import java.time.LocalDateTime;

public record HealthResponse(
        String message,
        String environment,
        LocalDateTime dateTime
) {
}
