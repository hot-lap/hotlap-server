package com.oops.application.post.model.command;

import com.oops.domain.post.model.enums.MismatchCategory;

public record PostUpdateCommand(
        String content,
        int impactIntensity,
        MismatchCategory category,
        String cause,
        String feeling
) {

}
