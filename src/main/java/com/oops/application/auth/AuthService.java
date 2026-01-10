package com.oops.application.auth;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidTokenException;
import com.oops.common.exception.NoAuthorityException;
import com.oops.domain.auth.model.AuthContextImpl;
import com.oops.domain.auth.model.AuthUser;
import com.oops.domain.auth.model.AuthUserImpl;
import com.oops.domain.auth.model.AuthUserToken;
import com.oops.domain.auth.model.RefreshToken;
import com.oops.domain.auth.model.TokenContext;
import com.oops.domain.auth.repository.RefreshTokenCommandRepository;
import com.oops.domain.user.repository.UserQueryRepository;
import com.oops.inbound.controller.auth.model.request.TokenRefreshRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserQueryRepository userQueryRepository;

    private final RefreshTokenCommandRepository refreshTokenCommandRepository;

    private final JwtTokenService jwtTokenService;

    public AuthUser resolveAuthUser(AuthUserToken token) {
        var payload = jwtTokenService.verifyToken(token);

        if (!payload.isAccessToken()) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        var user = userQueryRepository.findByIdOrThrow(payload.getId());

        return new AuthUserImpl(user.getId(), new AuthContextImpl(user.getName()));
    }

    public Long getUidFromTokenOrNull(AuthUserToken token) {
        try {
            return jwtTokenService.decodeToken(token).getId();
        } catch (Exception e) {
            return null;
        }
    }

    public Long getUidFromTokenOrThrow(AuthUserToken token) {
        var uid = getUidFromTokenOrNull(token);
        if (uid == null) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
        }
        return uid;
    }

    @Transactional
    public void logout(AuthUser user) {
        refreshTokenCommandRepository.deleteByUid(user.getUid());
    }

    @Transactional
    public TokenContext refreshToken(TokenRefreshRequest request) {
        var accessPayload = jwtTokenService.verifyTokenWithExtendedExpiredAt(request.getAccessToken());
        var refreshPayload = jwtTokenService.verifyRefreshToken(request.getRefreshToken());

        if (!accessPayload.getId().equals(refreshPayload.getId())) {
            throw new NoAuthorityException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        refreshTokenCommandRepository.deleteByUid(refreshPayload.getId());

        var tokenDto = jwtTokenService.generateAccessAndRefreshToken(refreshPayload.getId());

        refreshTokenCommandRepository.save(new RefreshToken(refreshPayload.getId(), tokenDto.refreshToken()));

        return tokenDto;
    }
}
