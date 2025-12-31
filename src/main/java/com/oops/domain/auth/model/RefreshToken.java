package com.oops.domain.auth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class RefreshToken {

	/** 리프레시 토큰 해당 유저 */
	private final Long uid;

	/** 리프레시 토큰 */
	@Setter
	private String refreshToken;

	public RefreshToken(Long uid, String refreshToken) {
		this.uid = uid;
		this.refreshToken = refreshToken;
	}

}
