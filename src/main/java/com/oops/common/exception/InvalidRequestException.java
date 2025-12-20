package com.oops.common.exception;

public class InvalidRequestException extends ApplicationException {

	public InvalidRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

}
