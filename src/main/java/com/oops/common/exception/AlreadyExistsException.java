package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.CONFLICT;

	public AlreadyExistsException(ErrorCode errorCode) {
		super(errorCode, status);
	}

}
