package com.oops.inbound.advice;

import com.oops.common.exception.ErrorCode;
import com.oops.inbound.advice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("[ERROR] Exception -> {}", e.getMessage(), e);

        var type = ErrorCode.INTERNAL_SERVER_ERROR;

        String message = resolveMessage(e, type);

        return new ErrorResponse(type.name(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .orElse("Invalid request");

        return new ErrorResponse(
                ErrorCode.INVALID_REQUEST_ERROR.name(),
                message
        );
    }

    private String resolveMessage(Exception e, ErrorCode code) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
            return code.getMessage();
        }

        return e.getMessage();
    }
}
