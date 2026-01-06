package com.oops.common.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.oops.config.encryption.EncryptProperties;

@Slf4j
@Configuration
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptConfig {

	@Bean
	public Encryptor encryptor(EncryptProperties properties) {
		log.info("initialized encryptor. key: {} algorithm: {}", properties.key(), properties.algorithm());
		return new Encryptor(properties.key(), properties.algorithm());
	}

}
