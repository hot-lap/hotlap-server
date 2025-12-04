package com.hotlap.server.inbound.controller.foo.model.request;

import com.hotlap.server.application.foo.model.command.FooRegisterCommand;

public record FooRegisterRequest(
        String description
) {
    public FooRegisterCommand toCommand() {
        return new FooRegisterCommand(description);
    }
}
