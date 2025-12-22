package com.oops.inbound.controller.post.model.response;

public record SuccessResponse(
        String message
) {

    public static SuccessResponse from(String message) {
        return new SuccessResponse(message);
    }
}
