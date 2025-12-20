package com.oops.common.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

	private final ErrorCode errorCode;

	private String message;

	private String[] args;

	private Object data;

	public ApplicationException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ApplicationException(ErrorCode errorCode, String message) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.message = message;
	}

	public ApplicationException(ErrorCode errorCode, Object data, String... args) {
		super(errorCode.getMessage());
		this.data = data;
		this.args = args;
		this.errorCode = errorCode;
	}

	public ApplicationException(ErrorCode errorCode, Throwable t) {
		super(t);
		this.errorCode = errorCode;
	}

	public ApplicationException(ErrorCode errorCode, Throwable t, String message) {
		super(t);
		this.errorCode = errorCode;
		this.message = message;
	}

}
