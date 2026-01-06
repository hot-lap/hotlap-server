package com.oops.inbound.controller.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequest {

	/**
	 * susu access token
	 */
	private String accessToken;

	/**
	 * susu refresh token
	 */
	private String refreshToken;

}
