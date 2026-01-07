package com.oops.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    private final HttpStatus httpStatus;

    private String message;

    private String[] args;

    private Object data;

    public ApplicationException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(ErrorCode errorCode, HttpStatus httpStatus, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApplicationException(ErrorCode errorCode, HttpStatus httpStatus, Object data, String... args) {
        super(errorCode.getMessage());
        this.httpStatus = httpStatus;
        this.data = data;
        this.args = args;
        this.errorCode = errorCode;
    }

    public ApplicationException(ErrorCode errorCode, Throwable t, HttpStatus httpStatus) {
        super(t);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(ErrorCode errorCode, Throwable t, HttpStatus httpStatus, String message) {
        super(t);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
