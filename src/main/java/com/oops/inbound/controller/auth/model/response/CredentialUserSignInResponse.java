package com.oops.inbound.controller.auth.model.response;

import com.oops.application.auth.model.result.CredentialUserSignInResult;
import com.oops.common.util.TimeZone;
import java.time.ZonedDateTime;

public record CredentialUserSignInResponse(
        Long uid,
        String accessToken,
        ZonedDateTime accessTokenExp,
        String refreshToken,
        ZonedDateTime refreshTokenExp) {
    public static CredentialUserSignInResponse of(CredentialUserSignInResult result) {
        return new CredentialUserSignInResponse(
                result.uid(),
                result.context().accessToken(),
                result.context().accessTokenExpiresAt().atZone(TimeZone.KOREA),
                result.context().refreshToken(),
                result.context().refreshTokenExpiresAt().atZone(TimeZone.KOREA));
    }
}
