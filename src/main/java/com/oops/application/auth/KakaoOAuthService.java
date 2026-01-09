package com.oops.application.auth;

import com.oops.application.auth.model.OAuthUserInfoModel;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.OAuthException;
import com.oops.common.util.StringUtil;
import com.oops.config.auth.KakaoOAuthProperties;
import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.outbound.kakao.KakaoOAuthClient;
import com.oops.outbound.kakao.KakaoOAuthTokenClient;
import com.oops.outbound.kakao.model.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthService implements OAuthService {

	private final KakaoOAuthClient kakaoOAuthClient;
	private final KakaoOAuthTokenClient kakaoOAuthTokenClient;
	private final KakaoOAuthProperties kakaoOAuthProperties;

	@Override
	public OAuthProvider getProvider() {
		return OAuthProvider.KAKAO;
	}

	@Override
	public OAuthUserInfoModel getOAuthUserInfo(String authorizationCode) {
		String accessToken = requestAccessToken(authorizationCode);
		return requestUserInfo(accessToken);
	}

	private String requestAccessToken(String authorizationCode) {
		try {
			MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
			formData.add("grant_type", "authorization_code");
			formData.add("client_id", kakaoOAuthProperties.clientId());
			formData.add("redirect_uri", kakaoOAuthProperties.redirectUri());
			formData.add("code", authorizationCode);

			if (StringUtil.isNotBlank(kakaoOAuthProperties.clientSecret())) {
				formData.add("client_secret", kakaoOAuthProperties.clientSecret());
			}

			var response = kakaoOAuthTokenClient.getToken(formData);
			if (response == null || response.accessToken() == null || response.accessToken().isBlank()) {
				throw new OAuthException(ErrorCode.KAKAO_OAUTH_FAILED_ERROR);
			}
			return response.accessToken();
		}
		catch (OAuthException e) {
			throw e;
		}
		catch (Exception e) {
			log.error("Kakao OAuth 토큰 요청 실패: {}", e.getMessage(), e);
			throw new OAuthException(ErrorCode.KAKAO_OAUTH_FAILED_ERROR);
		}
	}

	private OAuthUserInfoModel requestUserInfo(String accessToken) {
		try {
			var response = kakaoOAuthClient.getUserInfo("Bearer " + accessToken);
			if (response == null || response.id() == null) {
				throw new OAuthException(ErrorCode.KAKAO_OAUTH_FAILED_ERROR);
			}

            return extractUserInfo(response);
        }
		catch (OAuthException e) {
			throw e;
		}
		catch (Exception e) {
			log.error("Kakao Oauth API 호출 실패: {}", e.getMessage(), e);
			throw new OAuthException(ErrorCode.KAKAO_OAUTH_FAILED_ERROR);
		}
	}

    private static OAuthUserInfoModel extractUserInfo(KakaoUserInfoResponse response) {
        var account = response.kakaoAccount();

        if (account == null) {
            return new OAuthUserInfoModel(String.valueOf(response.id()), null, null);
        }

        String email = StringUtil.nullIfBlank(account.email());
        String name = account.profile() != null
                ? StringUtil.nullIfBlank(account.profile().nickname())
                : null;

        return new OAuthUserInfoModel(String.valueOf(response.id()), email, name);
    }

}
