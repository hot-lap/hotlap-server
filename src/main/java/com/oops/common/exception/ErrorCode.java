package com.oops.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	/**
	 * General
	 */
	INTERNAL_SERVER_ERROR("예기치 못한 에러가 발생했습니다."), INVALID_REQUEST_ERROR("잘못된 요청입니다."), BAD_REQUEST_ERROR("부적절한 요청입니다."),

	/**
	 * Auth
	 */
	NO_AUTHORITY_ERROR("권한이 없습니다."), INVALID_TOKEN_ERROR("유효한 토큰이 아닙니다."),
	INVALID_REFRESH_TOKEN_ERROR("유효한 리프레시 토큰이 아닙니다."), INVALID_ACCESS_TOKEN_ERROR("유효한 액세스 토큰이 아닙니다."),
	GOOGLE_OAUTH_FAILED_ERROR("Google OAuth 인증에 실패했습니다."), NOT_FOUND_USER_FOR_OAUTH_ERROR("OAuth 사용자를 찾을 수 없습니다."),
	NOT_SUPPORTED_OAUTH_PROVIDER("지원하지 않는 OAuth 제공자입니다."), NOT_FOUND_USER_ERROR("사용자를 찾을 수 없습니다."),

	/**
	 * Validation
	 */
	REQUIRED_PARAMETER_MISSING_ERROR("필수 파라미터가 누락되었습니다."),

	/**
	 * Encryption
	 */
	ENCRYPTION_ERROR("암호화에 실패했습니다."), DECRYPTION_ERROR("복호화에 실패했습니다."),

	/**
	 * ETC
	 */
	NOT_FOUND_FOO_ERROR("foo를 찾을 수 없습니다."),;

	private final String message;

}
