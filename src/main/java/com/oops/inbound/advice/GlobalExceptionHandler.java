package com.oops.inbound.advice;

import com.oops.common.exception.ApplicationException;
import com.oops.common.exception.ErrorCode;
import com.oops.inbound.advice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception e) {
		return switch (e) {
			case MethodArgumentNotValidException mae -> createErrorResponse(mae, HttpStatus.BAD_REQUEST);

			default -> {
				log.error("[ERROR] Unhandled Exception -> {}", e.getMessage(), e);
				yield new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						ErrorCode.INTERNAL_SERVER_ERROR.name(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
			}
		};
	}

	private ErrorResponse createErrorResponse(ApplicationException exception, HttpStatus httpStatus) {
		log.warn("[WARN] {} -> {}", exception.getClass().getSimpleName(), exception.getMessage());

		String message = exception.getMessage() != null && !exception.getMessage().isBlank() ? exception.getMessage()
				: exception.getErrorCode().getMessage();

		return new ErrorResponse(httpStatus.value(), exception.getErrorCode().name(), message);
	}

	private ErrorResponse createErrorResponse(MethodArgumentNotValidException exception, HttpStatus httpStatus) {
		log.warn("[WARN] {} -> {}", exception.getClass().getSimpleName(), exception.getMessage());

		String message = exception.getBindingResult()
			.getFieldErrors()
			.stream()
			.findFirst()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.orElse(ErrorCode.INVALID_REQUEST_ERROR.getMessage());

		return new ErrorResponse(httpStatus.value(), ErrorCode.INVALID_REQUEST_ERROR.name(), message);
	}

}
