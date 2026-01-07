package com.oops.common.encrypt;

import com.oops.common.encryption.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Converter
@Component
@RequiredArgsConstructor
public class EncryptConverter implements AttributeConverter<EncryptData, String> {

    private final EncryptionService encryptionService;

    @Override
    public String convertToDatabaseColumn(EncryptData attribute) {
        if (attribute == null) {
            return null;
        }
        return encryptionService.encrypt(attribute.getEncData());
    }

    @Override
    public EncryptData convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new EncryptData(encryptionService.decrypt(dbData));
    }
}
