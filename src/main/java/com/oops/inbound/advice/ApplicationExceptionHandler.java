package com.oops.inbound.advice;

import com.oops.common.exception.ApplicationException;
import com.oops.inbound.advice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
        log.error("[ERROR] ApplicationException -> {}", e.getMessage(), e);
        var response = createErrorResponse(e);
        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

    private ErrorResponse createErrorResponse(ApplicationException exception) {
        log.warn("[WARN] {} -> {}", exception.getClass().getSimpleName(), exception.getMessage());

        String message =
                exception.getMessage() != null && !exception.getMessage().isBlank()
                        ? exception.getMessage()
                        : exception.getErrorCode().getMessage();

        return new ErrorResponse(
                exception.getHttpStatus().value(), exception.getErrorCode().name(), message);
    }
}
