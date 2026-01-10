package com.oops.application.auth.model.result;

import com.oops.domain.auth.model.TokenContext;

public record AuthResult(Long uid, TokenContext tokens) {
    public static AuthResult of(Long uid, TokenContext tokens) {
        return new AuthResult(uid, tokens);
    }
}
