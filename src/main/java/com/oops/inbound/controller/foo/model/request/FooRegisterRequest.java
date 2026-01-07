package com.oops.inbound.controller.foo.model.request;

import com.oops.application.foo.model.command.FooRegisterCommand;

public record FooRegisterRequest(String description) {
    public FooRegisterCommand toCommand() {
        return new FooRegisterCommand(description);
    }
}
