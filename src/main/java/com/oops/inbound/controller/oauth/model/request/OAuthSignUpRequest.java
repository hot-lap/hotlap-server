package com.oops.inbound.controller.oauth.model.request;

import com.oops.application.auth.model.command.OAuthSignUpCommand;
import com.oops.domain.user.model.vo.OAuthProvider;
import jakarta.validation.constraints.NotBlank;

public record OAuthSignUpRequest(@NotBlank(message = "액세스 토큰은 필수입니다") String accessToken) {
	public OAuthSignUpCommand toCommand(String provider) {
		return new OAuthSignUpCommand(OAuthProvider.from(provider), accessToken);
	}
}
