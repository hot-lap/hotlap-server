package com.oops.application.user;

import com.oops.application.user.model.result.OAuthUserResult;
import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.domain.user.repository.OAuthUserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthUserInquiryService {

	private final OAuthUserQueryRepository oAuthUserQueryRepository;

	public Optional<OAuthUserResult> findByProviderAndOauthId(OAuthProvider provider, String oauthId) {
		return oAuthUserQueryRepository.findByProviderAndOauthId(provider, oauthId).map(OAuthUserResult::from);
	}

	public boolean existsByProviderAndOauthId(OAuthProvider provider, String oauthId) {
		return oAuthUserQueryRepository.existsByProviderAndOauthId(provider, oauthId);
	}

}
