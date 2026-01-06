package com.oops.inbound.controller.oauth.model.response;

import com.oops.application.auth.model.TokenContext;
import com.oops.application.auth.model.result.AuthResult;

public record AuthResponse(Long uid, TokenContext tokens) {
	public static AuthResponse from(AuthResult result) {
		return new AuthResponse(result.uid(), result.tokens());
	}
}
