package com.oops.domain.auth.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenContext {

	private String accessToken;

	private LocalDateTime accessTokenExp;

	private String refreshToken;

	private LocalDateTime refreshTokenExp;

}
