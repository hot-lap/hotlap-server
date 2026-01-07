package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class JwtException extends ApplicationException {

    private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public JwtException(ErrorCode errorCode) {
        super(errorCode, status);
    }

    public JwtException(String message) {
        super(ErrorCode.INVALID_ACCESS_TOKEN_ERROR, status, message);
    }
}
