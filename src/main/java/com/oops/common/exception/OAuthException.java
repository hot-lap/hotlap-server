package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class OAuthException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

	public OAuthException(ErrorCode errorCode) {
		super(errorCode, status);
	}

	public OAuthException(String message) {
		super(ErrorCode.GOOGLE_OAUTH_FAILED_ERROR, status, message);
	}

}
