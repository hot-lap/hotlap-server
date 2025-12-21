package com.oops.common.encryption;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Converter
@Component
@RequiredArgsConstructor
public class EncryptConverter implements AttributeConverter<String, String> {

	private final EncryptionService encryptionService;

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return encryptionService.encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return encryptionService.decrypt(dbData);
	}

}
