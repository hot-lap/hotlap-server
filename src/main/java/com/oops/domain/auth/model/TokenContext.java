package com.oops.domain.auth.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenContext {

    private String accessToken;

    private LocalDateTime accessTokenExp;

    private String refreshToken;

    private LocalDateTime refreshTokenExp;
}
