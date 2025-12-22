package com.oops.inbound.controller.post.model.request;

import com.oops.application.post.model.command.PostCreateCommand;
import com.oops.domain.post.model.enums.MismatchCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(
        @NotBlank
        String content,

        @Min(1) @Max(5)
        int impactIntensity,

        @NotNull
        MismatchCategory category,

        String cause,
        String feeling
) {

    public PostCreateCommand toCommand() {
        return new PostCreateCommand(content, impactIntensity, category, cause, feeling);
    }
}
