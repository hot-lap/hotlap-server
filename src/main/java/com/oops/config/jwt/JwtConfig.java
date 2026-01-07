package com.oops.config.jwt;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtConfig.JwtProperties.class)
public class JwtConfig {

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties(prefix = "auth.jwt")
    public static class JwtProperties {

        @NotBlank
        private String secret = "";

        @Min(1)
        private int accessExp = 0;

        @Min(1)
        private int refreshExp = 0;

        private String issuer = "cinement-api";

        private String audience = "cinement-api";
    }
}
