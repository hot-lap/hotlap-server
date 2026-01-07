package com.oops.config.encryption;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt")
public record EncryptProperties(String key, String algorithm) {}
