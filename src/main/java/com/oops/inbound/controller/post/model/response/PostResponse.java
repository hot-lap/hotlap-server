package com.oops.inbound.controller.post.model.response;

import com.oops.application.post.model.result.PostResult;
import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String content,
        int impactIntensity,
        String category,
        String cause,
        String feeling,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static PostResponse from(PostResult postResult) {
        return new PostResponse(
                postResult.id(),
                postResult.content(),
                postResult.impactIntensity(),
                postResult.category(),
                postResult.cause(),
                postResult.feeling(),
                postResult.createdAt(),
                postResult.modifiedAt()
        );
    }

}
