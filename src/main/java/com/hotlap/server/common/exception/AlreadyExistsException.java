package com.hotlap.server.common.exception;

public class AlreadyExistsException extends BusinessException {
    public AlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
