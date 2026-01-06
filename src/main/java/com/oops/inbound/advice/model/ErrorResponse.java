package com.oops.inbound.advice.model;

public record ErrorResponse(int status, String errorCode, String reason) {
	public ErrorResponse(String code, String message) {
		this(500, code, message);
	}
}
