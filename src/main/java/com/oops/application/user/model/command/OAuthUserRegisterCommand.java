package com.oops.application.user.model.command;

import com.oops.domain.user.model.vo.OAuthProvider;

public record OAuthUserRegisterCommand(OAuthProvider provider, String oauthId, Long userId) {
}
