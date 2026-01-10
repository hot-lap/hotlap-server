package com.oops.application.auth.model.command;

public record CredentialAuthRegisterCommand(String username, String password, String name, String nickname) {}
