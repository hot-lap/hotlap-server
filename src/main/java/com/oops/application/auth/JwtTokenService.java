package com.oops.application.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oops.application.auth.model.TokenContext;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidTokenException;
import com.oops.config.auth.JwtConfig;
import com.oops.domain.auth.model.AuthUserToken;
import com.oops.domain.auth.model.AuthUserTokenPayload;
import com.oops.domain.auth.model.RefreshToken;
import com.oops.outbound.mysql.auth.repository.RefreshTokenJpaRepository;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private static final String ACCESS_TOKEN = "accessToken";

    private static final String REFRESH_TOKEN = "refreshToken";

    private final JwtConfig jwtConfig;

    private final RefreshTokenJpaRepository refreshTokenRepository;

    private final ObjectMapper mapper;

    private JWTVerifier accessJwtVerifier;

    private JWTVerifier accessJwtVerifierWithExtendedExpiredAt;

    private JWTVerifier refreshJwtVerifier;

    /**
     * Spring DI 완료 후 안전하게 초기화
     */
    @PostConstruct
    private void init() {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.secret());

        accessJwtVerifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.issuer())
                .withAudience(jwtConfig.audience())
                .withClaim("type", ACCESS_TOKEN)
                .build();

        accessJwtVerifierWithExtendedExpiredAt = JWT.require(algorithm)
                .withIssuer(jwtConfig.issuer())
                .withAudience(jwtConfig.audience())
                .withClaim("type", ACCESS_TOKEN)
                .acceptExpiresAt(jwtConfig.refreshExp())
                .build();

        refreshJwtVerifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.issuer())
                .withAudience(jwtConfig.audience())
                .withClaim("type", REFRESH_TOKEN)
                .build();
    }

    private String createToken(Long id, LocalDateTime expiredAt, String type) {
        Instant instant = expiredAt.atZone(ZoneId.systemDefault()).toInstant();

        return JWT.create()
                .withIssuer(jwtConfig.issuer())
                .withAudience(jwtConfig.audience())
                .withClaim("id", id)
                .withClaim("type", type)
                .withExpiresAt(Date.from(instant))
                .sign(Algorithm.HMAC256(jwtConfig.secret()));
    }

    public AuthUserTokenPayload verifyToken(AuthUserToken token) {
        try {
            String payload =
                    decodePayload(accessJwtVerifier.verify(token.getValue()).getPayload());
            return mapper.readValue(payload, AuthUserTokenPayload.class);
        } catch (Exception e) {
            log.warn("Access token verification failed: {}", e.getMessage());
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    public AuthUserTokenPayload decodeToken(AuthUserToken token) {
        try {
            String payload = decodePayload(JWT.decode(token.getValue()).getPayload());
            return mapper.readValue(payload, AuthUserTokenPayload.class);
        } catch (Exception e) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    public AuthUserTokenPayload verifyTokenWithExtendedExpiredAt(String token) {
        try {
            String payload = decodePayload(
                    accessJwtVerifierWithExtendedExpiredAt.verify(token).getPayload());
            return mapper.readValue(payload, AuthUserTokenPayload.class);
        } catch (Exception e) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    public TokenContext generateAccessAndRefreshToken(Long uid) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime accessExp = now.plusSeconds(jwtConfig.accessExp());
        LocalDateTime refreshExp = now.plusSeconds(jwtConfig.refreshExp());

        Instant accessExpInstant = accessExp.atZone(ZoneId.systemDefault()).toInstant();
        Instant refreshExpInstant = refreshExp.atZone(ZoneId.systemDefault()).toInstant();

        return new TokenContext(
                createToken(uid, accessExp, ACCESS_TOKEN),
                accessExpInstant,
                createToken(uid, refreshExp, REFRESH_TOKEN),
                refreshExpInstant);
    }

    public AuthUserTokenPayload verifyRefreshToken(String refreshToken) {
        try {
            String payload =
                    decodePayload(refreshJwtVerifier.verify(refreshToken).getPayload());
            return mapper.readValue(payload, AuthUserTokenPayload.class);
        } catch (Exception e) {
            throw new InvalidTokenException(ErrorCode.INVALID_REFRESH_TOKEN_ERROR);
        }
    }

    public void deleteByKey(String key) {
        refreshTokenRepository.deleteByRefreshToken(key);
    }

    public void save(RefreshToken token) {
        refreshTokenRepository.save(token);
    }

    private String decodePayload(String base64Payload) {
        return new String(Base64.getUrlDecoder().decode(base64Payload), StandardCharsets.UTF_8);
    }
}
