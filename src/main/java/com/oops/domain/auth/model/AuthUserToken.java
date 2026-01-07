package com.oops.domain.auth.model;

import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
public class AuthUserToken {

    private final String key;

    private final String value;

    public AuthUserToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public AuthUserToken(String value) {
        this.key = AuthConstants.AUTH_TOKEN_KEY;
        this.value = value;
    }

    public boolean isInvalid() {
        return "token".equals(key) && (value == null || value.isBlank());
    }

    public static AuthUserToken from(String value) {
        return new AuthUserToken(value);
    }

    public static AuthUserToken resolve(HttpHeaders headers) {
        String value = headers.getFirst(AuthConstants.AUTH_TOKEN_KEY);

        if (value == null || value.isBlank()) {
            return new AuthUserToken("token", "");
        }

        return AuthUserToken.from(value);
    }
}
