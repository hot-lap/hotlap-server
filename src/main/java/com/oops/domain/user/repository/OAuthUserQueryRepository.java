package com.oops.domain.user.repository;

import com.oops.domain.user.model.OAuthUser;
import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.outbound.mysql.user.repository.OAuthUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthUserQueryRepository {

	private final OAuthUserJpaRepository oAuthUserJpaRepository;

	public Optional<OAuthUser> findByProviderAndOauthId(OAuthProvider provider, String oauthId) {
		return oAuthUserJpaRepository.findByProviderAndOauthId(provider, oauthId);
	}

	public boolean existsByProviderAndOauthId(OAuthProvider provider, String oauthId) {
		return oAuthUserJpaRepository.existsByProviderAndOauthId(provider, oauthId);
	}

}
