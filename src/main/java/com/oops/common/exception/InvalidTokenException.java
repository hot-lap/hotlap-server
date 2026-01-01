package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

	public InvalidTokenException(ErrorCode errorCode) {
		super(errorCode, status);
	}

}
