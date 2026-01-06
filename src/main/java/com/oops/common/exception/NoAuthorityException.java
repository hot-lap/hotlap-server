package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class NoAuthorityException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.FORBIDDEN;

	public NoAuthorityException(ErrorCode errorCode) {
		super(errorCode, status);
	}

}
