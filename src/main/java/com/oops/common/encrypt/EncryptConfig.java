package com.oops.common.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(EncryptConfig.EncryptProperty.class)
public class EncryptConfig {

	@ConfigurationProperties(prefix = "encrypt")
	public static class EncryptProperty {

		private String key;

		private String algorithm;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getAlgorithm() {
			return algorithm;
		}

		public void setAlgorithm(String algorithm) {
			this.algorithm = algorithm;
		}

	}

	@Bean
	public Encryptor encryptor(EncryptProperty property) {
		log.info("initialized encryptor. key: {} algorithm: {}", property.getKey(), property.getAlgorithm());
		return new Encryptor(property.getKey(), property.getAlgorithm());
	}

}
