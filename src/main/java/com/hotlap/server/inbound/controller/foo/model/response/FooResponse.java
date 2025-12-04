package com.hotlap.server.inbound.controller.foo.model.response;

import com.hotlap.server.application.foo.model.result.FooResult;

import java.time.LocalDateTime;

public record FooResponse(
        Long id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static FooResponse from(FooResult result) {
        return new FooResponse(
                result.id(),
                result.description(),
                result.createdAt(),
                result.modifiedAt()
        );
    }
}
