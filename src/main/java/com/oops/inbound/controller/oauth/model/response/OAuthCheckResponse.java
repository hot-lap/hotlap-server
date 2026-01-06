package com.oops.inbound.controller.oauth.model.response;

import com.oops.application.auth.model.result.OAuthCheckResult;

public record OAuthCheckResponse(boolean isExists) {
	public static OAuthCheckResponse from(OAuthCheckResult result) {
		return new OAuthCheckResponse(result.isExists());
	}
}
