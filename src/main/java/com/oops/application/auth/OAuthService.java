package com.oops.application.auth;

import com.oops.application.auth.model.OAuthUserInfoModel;
import com.oops.domain.user.model.vo.OAuthProvider;

public interface OAuthService {

	OAuthProvider getProvider();

	OAuthUserInfoModel getOAuthUserInfo(String authorizationCode);
}
