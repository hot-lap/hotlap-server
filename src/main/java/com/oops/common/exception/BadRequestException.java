package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.BAD_REQUEST;

	public BadRequestException(ErrorCode errorCode) {
		super(errorCode, status);
	}

	public BadRequestException(ErrorCode errorCode, String message) {
		super(errorCode, status, message);
	}

}
