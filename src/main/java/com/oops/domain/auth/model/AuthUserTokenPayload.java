package com.oops.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthUserTokenPayload {

	private final Long id;

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private final List<String> aud;

	private final String iss;

	private final Long exp;

	private final String type;

}
