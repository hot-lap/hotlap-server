package com.oops.application.auth.model.command;

import com.oops.domain.user.model.vo.OAuthProvider;

public record OAuthSignUpCommand(OAuthProvider provider, String accessToken) {}
