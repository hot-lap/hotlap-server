package com.hotlap.server.inbound.advice;

import com.hotlap.server.common.exception.BusinessException;
import com.hotlap.server.common.exception.ErrorCode;
import com.hotlap.server.inbound.advice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException e) {
        log.error("[ERROR] BusinessException -> {}", e.getMessage());

        String message = resolveMessage(e, e.getErrorCode());

        return new ErrorResponse(e.getErrorCode().name(), message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("[ERROR] Exception -> {}", e.getMessage());

        var type = ErrorCode.INTERNAL_SERVER_ERROR;

        String message = resolveMessage(e, type);

        return new ErrorResponse(type.name(), message);
    }

    private static String resolveMessage(Exception e, ErrorCode code) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
            return code.getMessage();
        }

        return e.getMessage();
    }
}
