package com.oops.config.encryption;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.encryption")
public record EncryptionConfig(String key) {
}
