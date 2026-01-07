package com.oops.domain.user.model.vo;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidRequestException;
import java.util.Arrays;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OAuthProvider {
    GOOGLE("google"),
    ;

    private final String value;

    public static OAuthProvider from(String value) {
        return Arrays.stream(values())
                .filter(provider -> provider.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException(ErrorCode.NOT_SUPPORTED_OAUTH_PROVIDER));
    }
}
