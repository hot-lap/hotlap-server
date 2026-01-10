package com.oops.inbound.controller.auth.model.request;

import com.oops.application.auth.model.command.CredentialUserSignInCommand;

public record CredentialUserSignInRequest(String username, String password) {
    public CredentialUserSignInCommand toCommand() {
        return new CredentialUserSignInCommand(username, password);
    }
}
