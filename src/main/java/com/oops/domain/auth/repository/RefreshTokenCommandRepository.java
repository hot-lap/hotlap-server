package com.oops.domain.auth.repository;

import com.oops.domain.auth.model.RefreshToken;
import com.oops.outbound.mysql.auth.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenCommandRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Transactional
    public void deleteByUid(Long uid) {
        refreshTokenJpaRepository.deleteByUid(uid);
    }

    @Transactional
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }
}
