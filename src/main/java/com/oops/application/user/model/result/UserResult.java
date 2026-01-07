package com.oops.application.user.model.result;

import com.oops.domain.user.model.User;
import java.time.LocalDateTime;

public record UserResult(Long id, String email, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static UserResult from(User user) {
        return new UserResult(
                user.getId(), user.getEmail().getEncData(), user.getName(), user.getCreatedAt(), user.getModifiedAt());
    }
}
