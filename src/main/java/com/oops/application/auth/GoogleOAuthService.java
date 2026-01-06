package com.oops.application.auth;

import com.oops.application.auth.model.OAuthUserInfoModel;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.OAuthException;
import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.outbound.google.GoogleOAuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleOAuthService implements OAuthService {

	private final GoogleOAuthClient googleOAuthClient;

	@Override
	public OAuthProvider getProvider() {
		return OAuthProvider.GOOGLE;
	}

	@Override
	public OAuthUserInfoModel getOAuthUserInfo(String accessToken) {
		try {
			var response = googleOAuthClient.getUserInfo("Bearer " + accessToken);
			return new OAuthUserInfoModel(response.sub(), response.email(), response.name());
		}
		catch (Exception e) {
			log.error("Google Oauth API 호출 실패: {}", e.getMessage(), e);
			throw new OAuthException(ErrorCode.GOOGLE_OAUTH_FAILED_ERROR);
		}
	}

}
