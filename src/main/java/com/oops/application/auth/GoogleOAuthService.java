package com.oops.application.auth;

import com.oops.application.auth.model.OAuthUserInfoModel;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.OAuthException;
import com.oops.config.auth.GoogleOAuthProperties;
import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.outbound.google.GoogleOAuthClient;
import com.oops.outbound.google.GoogleOAuthTokenClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleOAuthService implements OAuthService {

    private final GoogleOAuthClient googleOAuthClient;
    private final GoogleOAuthTokenClient googleOAuthTokenClient;
    private final GoogleOAuthProperties googleOAuthProperties;

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public OAuthUserInfoModel getOAuthUserInfo(String authorizationCode) {
        String accessToken = requestAccessToken(authorizationCode);
        return requestUserInfo(accessToken);
    }

    private String requestAccessToken(String authorizationCode) {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("code", authorizationCode);
            formData.add("client_id", googleOAuthProperties.clientId());
            formData.add("client_secret", googleOAuthProperties.clientSecret());
            formData.add("redirect_uri", googleOAuthProperties.redirectUri());
            formData.add("grant_type", "authorization_code");

            var response = googleOAuthTokenClient.getToken(formData);
            if (response == null || response.accessToken() == null || response.accessToken().isBlank()) {
                throw new OAuthException(ErrorCode.GOOGLE_OAUTH_FAILED_ERROR);
            }
            return response.accessToken();
        } catch (OAuthException e) {
            throw e;
        } catch (Exception e) {
            log.error("Google OAuth 토큰 요청 실패: {}", e.getMessage(), e);
            throw new OAuthException(ErrorCode.GOOGLE_OAUTH_FAILED_ERROR);
        }
    }

    private OAuthUserInfoModel requestUserInfo(String accessToken) {
        try {
            var response = googleOAuthClient.getUserInfo("Bearer " + accessToken);
            return new OAuthUserInfoModel(response.sub(), response.email(), response.name());
        } catch (Exception e) {
            log.error("Google Oauth API 호출 실패: {}", e.getMessage(), e);
            throw new OAuthException(ErrorCode.GOOGLE_OAUTH_FAILED_ERROR);
        }
    }
}
