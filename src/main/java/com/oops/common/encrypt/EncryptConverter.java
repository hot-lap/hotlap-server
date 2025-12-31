package com.oops.common.encrypt;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptConverter implements AttributeConverter<EncryptData, String> {

	@Override
	public String convertToDatabaseColumn(EncryptData attribute) {
		return attribute != null ? attribute.getEncData() : null;
	}

	@Override
	public EncryptData convertToEntityAttribute(String dbData) {
		return dbData != null ? new EncryptData(dbData) : null;
	}

}
