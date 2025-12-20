package com.oops.common.exception;

public class BadRequestException extends ApplicationException {

	public BadRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

	public BadRequestException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
