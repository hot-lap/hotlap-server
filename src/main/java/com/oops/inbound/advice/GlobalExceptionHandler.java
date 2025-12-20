package com.oops.inbound.advice;

import com.oops.common.exception.*;
import com.oops.inbound.advice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception e) {
		return switch (e) {
			case NotFoundException nfe -> createErrorResponse(nfe, HttpStatus.NOT_FOUND);

			case AlreadyExistsException aee -> createErrorResponse(aee, HttpStatus.CONFLICT);

			case InvalidRequestException ire -> createErrorResponse(ire, HttpStatus.BAD_REQUEST);

			case BadRequestException bre -> createErrorResponse(bre, HttpStatus.BAD_REQUEST);

			case MethodArgumentNotValidException mae -> createErrorResponse(mae, HttpStatus.BAD_REQUEST);

			case ApplicationException ae -> createErrorResponse(ae, HttpStatus.INTERNAL_SERVER_ERROR);

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
