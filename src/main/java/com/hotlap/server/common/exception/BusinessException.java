package com.hotlap.server.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private String message;
    private String[] args;
    private Object data;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode, Object data, String... args) {
        super(errorCode.getMessage());
        this.data = data;
        this.args = args;
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable t, String message) {
        super(t);
        this.errorCode = errorCode;
        this.message = message;
    }
}
