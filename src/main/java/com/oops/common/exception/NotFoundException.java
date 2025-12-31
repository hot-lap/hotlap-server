package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

	private static final HttpStatus status = HttpStatus.NOT_FOUND;

	public NotFoundException(ErrorCode errorCode) {
		super(errorCode, status);
	}

}
