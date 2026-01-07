package com.oops.inbound.controller.health.model.response;

import java.time.LocalDateTime;

public record HealthResponse(String message, String environment, LocalDateTime dateTime) {}
