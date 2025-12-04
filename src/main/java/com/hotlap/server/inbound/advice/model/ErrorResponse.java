package com.hotlap.server.inbound.advice.model;

public record ErrorResponse(
        String errorCode,
        String reason
) {
}
