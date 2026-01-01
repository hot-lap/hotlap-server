package com.oops.application.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidTokenException;
import com.oops.config.jwt.JwtConfig;
import com.oops.domain.auth.model.AuthUserToken;
import com.oops.domain.auth.model.AuthUserTokenPayload;
import com.oops.domain.auth.model.RefreshToken;
import com.oops.domain.auth.model.TokenContext;
import com.oops.outbound.mysql.auth.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private static final String ACCESS_TOKEN = "accessToken";

	private static final String REFRESH_TOKEN = "refreshToken";

	private final JwtConfig.JwtProperties jwtProperties;

	private final RefreshTokenJpaRepository refreshTokenRepository;

	private final ObjectMapper mapper;

	private final Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

	private final JWTVerifier accessJwtVerifier = JWT.require(algorithm)
		.withIssuer(jwtProperties.getIssuer())
		.withAudience(jwtProperties.getAudience())
		.withClaim("type", ACCESS_TOKEN)
		.build();

	private final JWTVerifier accessJwtVerifierWithExtendedExpiredAt = JWT.require(algorithm)
		.withIssuer(jwtProperties.getIssuer())
		.withAudience(jwtProperties.getAudience())
		.withClaim("type", ACCESS_TOKEN)
		.acceptExpiresAt(jwtProperties.getRefreshExp())
		.build();

	private final JWTVerifier refreshJwtVerifier = JWT.require(algorithm)
		.withIssuer(jwtProperties.getIssuer())
		.withAudience(jwtProperties.getAudience())
		.withClaim("type", REFRESH_TOKEN)
		.build();

	private String createToken(Long id, LocalDateTime expiredAt, String type) {
		Instant instant = expiredAt.atZone(ZoneId.systemDefault()).toInstant();

		return JWT.create()
			.withIssuer(jwtProperties.getIssuer())
			.withAudience(jwtProperties.getAudience())
			.withClaim("id", id)
			.withClaim("type", type)
			.withExpiresAt(Date.from(instant))
			.sign(Algorithm.HMAC256(jwtProperties.getSecret()));
	}

	public AuthUserTokenPayload verifyToken(AuthUserToken token) {
		try {
			String payload = decodePayload(accessJwtVerifier.verify(token.getValue()).getPayload());
			return mapper.readValue(payload, AuthUserTokenPayload.class);
		}
		catch (Exception e) {
			log.warn("Access token verification failed: {}", e.getMessage());
			throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
		}
	}

	public AuthUserTokenPayload decodeToken(AuthUserToken token) {
		try {
			String payload = decodePayload(JWT.decode(token.getValue()).getPayload());
			return mapper.readValue(payload, AuthUserTokenPayload.class);
		}
		catch (Exception e) {
			throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
		}
	}

	public AuthUserTokenPayload verifyTokenWithExtendedExpiredAt(String token) {
		try {
			String payload = decodePayload(accessJwtVerifierWithExtendedExpiredAt.verify(token).getPayload());
			return mapper.readValue(payload, AuthUserTokenPayload.class);
		}
		catch (Exception e) {
			throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_ERROR);
		}
	}

	public TokenContext generateAccessAndRefreshToken(Long uid) {
		LocalDateTime now = LocalDateTime.now();

		LocalDateTime accessExp = now.plusSeconds(jwtProperties.getAccessExp());
		LocalDateTime refreshExp = now.plusSeconds(jwtProperties.getRefreshExp());

		return new TokenContext(createToken(uid, accessExp, ACCESS_TOKEN), accessExp,
				createToken(uid, refreshExp, REFRESH_TOKEN), refreshExp);
	}

	public AuthUserTokenPayload verifyRefreshToken(String refreshToken) {
		try {
			String payload = decodePayload(refreshJwtVerifier.verify(refreshToken).getPayload());
			return mapper.readValue(payload, AuthUserTokenPayload.class);
		}
		catch (Exception e) {
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
		byte[] decoded = Base64.getDecoder().decode(base64Payload);
		return new String(decoded, StandardCharsets.UTF_8);
	}

}
