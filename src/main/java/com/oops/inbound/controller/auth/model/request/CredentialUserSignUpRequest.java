package com.oops.inbound.controller.auth.model.request;

import com.oops.application.auth.model.command.CredentialAuthRegisterCommand;

public record CredentialUserSignUpRequest(String username, String password, String name, String nickname) {
    public CredentialAuthRegisterCommand toCommand() {
        return new CredentialAuthRegisterCommand(username, password, name(), nickname);
    }
}
