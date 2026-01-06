package com.oops.application.auth.model;

import java.time.Instant;

public record TokenContext(String accessToken, Instant accessTokenExpiresAt, String refreshToken,
		Instant refreshTokenExpiresAt) {
}
