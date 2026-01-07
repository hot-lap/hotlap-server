package com.oops.domain.user.repository;

import com.oops.domain.user.model.OAuthUser;
import com.oops.outbound.mysql.user.repository.OAuthUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class OAuthUserCommandRepository {

    private final OAuthUserJpaRepository oAuthUserJpaRepository;

    @Transactional
    public OAuthUser save(OAuthUser oAuthUser) {
        return oAuthUserJpaRepository.save(oAuthUser);
    }
}
