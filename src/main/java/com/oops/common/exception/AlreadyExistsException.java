package com.oops.common.exception;

public class AlreadyExistsException extends ApplicationException {

	public AlreadyExistsException(ErrorCode errorCode) {
		super(errorCode);
	}

}
