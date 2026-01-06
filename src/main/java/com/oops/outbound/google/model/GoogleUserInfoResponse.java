package com.oops.outbound.google.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUserInfoResponse(String sub, String email, @JsonProperty("email_verified") boolean emailVerified,
		String name, @JsonProperty("given_name") String givenName, @JsonProperty("family_name") String familyName,
		String picture, String locale) {
}
