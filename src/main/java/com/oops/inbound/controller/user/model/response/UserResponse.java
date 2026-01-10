package com.oops.inbound.controller.user.model.response;

import com.oops.application.auth.model.result.UserResult;

public record UserResponse(Long id, String name, String nickname) {
    public static UserResponse from(UserResult user) {
        return new UserResponse(user.getId(), user.getName(), user.getNickname());
    }
}
