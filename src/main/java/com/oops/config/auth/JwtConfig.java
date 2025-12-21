package com.oops.config.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.jwt")
public record JwtConfig(String secret, String issuer, String audience, Long accessExp, Long refreshExp) {
}
