package com.oops.application.post.model.result;

import com.oops.domain.post.model.Post;
import java.time.LocalDateTime;

public record PostResult(
        Long id,
        String content,
        int impactIntensity,
        String category,
        String cause,
        String feeling,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {

    public static PostResult from(Post post) {
        return new PostResult(
                post.getId(),
                post.getContent(),
                post.getImpactIntensity().value(),
                post.getCategory().name(),
                post.getCause(),
                post.getFeeling(),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }
}
