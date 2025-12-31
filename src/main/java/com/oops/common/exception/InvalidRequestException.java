package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.BAD_REQUEST;

	public InvalidRequestException(ErrorCode errorCode) {
		super(errorCode, status);
	}

}
