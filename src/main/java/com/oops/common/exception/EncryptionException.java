package com.oops.common.exception;

import org.springframework.http.HttpStatus;

public class EncryptionException extends ApplicationException {

    private static final HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

    public EncryptionException(ErrorCode errorCode) {
        super(errorCode, status);
    }
}
