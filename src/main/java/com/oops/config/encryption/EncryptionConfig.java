package com.oops.config.encryption;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt")
public record EncryptionConfig(String key) {
}
