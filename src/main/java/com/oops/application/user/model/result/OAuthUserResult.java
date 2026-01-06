package com.oops.application.user.model.result;

import com.oops.domain.user.model.OAuthUser;
import com.oops.domain.user.model.vo.OAuthProvider;

import java.time.LocalDateTime;

public record OAuthUserResult(Long id, OAuthProvider provider, String oauthId, Long userId, LocalDateTime createdAt,
		LocalDateTime modifiedAt) {
	public static OAuthUserResult from(OAuthUser oauthUser) {
		return new OAuthUserResult(oauthUser.getId(), oauthUser.getProvider(), oauthUser.getOauthId(),
				oauthUser.getUserId(), oauthUser.getCreatedAt(), oauthUser.getModifiedAt());
	}
}
