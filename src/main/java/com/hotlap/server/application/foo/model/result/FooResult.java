package com.hotlap.server.application.foo.model.result;

import com.hotlap.server.domain.foo.model.Foo;

import java.time.LocalDateTime;

public record FooResult(
        Long id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static FooResult from(Foo foo) {
        return new FooResult(
                foo.getId(),
                foo.getDescription(),
                foo.getCreatedAt(),
                foo.getModifiedAt()
        );
    }
}
