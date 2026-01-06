package com.oops.application.auth;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidRequestException;
import com.oops.domain.user.model.vo.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OAuthServiceProvider {

	private final Map<OAuthProvider, OAuthService> oauthServiceMap;

	public OAuthServiceProvider(List<OAuthService> oauthServices) {
		this.oauthServiceMap = oauthServices.stream()
			.collect(Collectors.toMap(OAuthService::getProvider, service -> service));
	}

	public OAuthService getService(OAuthProvider provider) {
		return Optional.ofNullable(oauthServiceMap.get(provider))
			.orElseThrow(() -> new InvalidRequestException(ErrorCode.NOT_SUPPORTED_OAUTH_PROVIDER));
	}

}
