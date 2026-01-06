package com.oops.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserTokenPayload {

	private final Long id;

	private final String aud;

	private final String iss;

	private final Long exp;

	private final String type;

}
