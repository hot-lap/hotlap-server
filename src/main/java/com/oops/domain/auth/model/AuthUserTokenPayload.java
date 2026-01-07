package com.oops.domain.auth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserTokenPayload {

    private static final String ACCESS_TOKEN = "accessToken";

    private static final String REFRESH_TOKEN = "refreshToken";

    private final Long id;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private final List<String> aud;

    private final String iss;

    private final Long exp;

    private final String type;

    public boolean isAccessToken() {
        return ACCESS_TOKEN.equals(type);
    }

    public boolean isRefreshToken() {
        return REFRESH_TOKEN.equals(type);
    }
}
