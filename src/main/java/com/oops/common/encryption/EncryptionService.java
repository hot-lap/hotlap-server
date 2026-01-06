package com.oops.common.encryption;

import com.oops.common.exception.EncryptionException;
import com.oops.common.exception.ErrorCode;
import com.oops.config.encryption.EncryptProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class EncryptionService {

	private final EncryptProperties encryptProperties;

	private static final String ALGORITHM = "AES/GCM/NoPadding";

	private static final int GCM_IV_LENGTH = 12;

	private static final int GCM_TAG_LENGTH = 128;

	public String encrypt(String plainText) {
		if (plainText == null || plainText.isBlank()) {
			return plainText;
		}

		try {
			byte[] iv = new byte[GCM_IV_LENGTH];
			SecureRandom random = new SecureRandom();
			random.nextBytes(iv);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(encryptProperties.key().getBytes(StandardCharsets.UTF_8), "AES");
			GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

			cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
			byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

			// IV + encrypted 합치기
			byte[] result = new byte[iv.length + encrypted.length];
			System.arraycopy(iv, 0, result, 0, iv.length);
			System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);

			return Base64.getEncoder().encodeToString(result);
		}
		catch (Exception e) {
			throw new EncryptionException(ErrorCode.ENCRYPTION_ERROR);
		}
	}

	public String decrypt(String encryptedText) {
		if (encryptedText == null || encryptedText.isBlank()) {
			return encryptedText;
		}

		try {
			byte[] decoded = Base64.getDecoder().decode(encryptedText);

			// IV 추출
			byte[] iv = new byte[GCM_IV_LENGTH];
			System.arraycopy(decoded, 0, iv, 0, iv.length);

			// 암호화된 데이터 추출
			byte[] encrypted = new byte[decoded.length - GCM_IV_LENGTH];
			System.arraycopy(decoded, GCM_IV_LENGTH, encrypted, 0, encrypted.length);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(encryptProperties.key().getBytes(StandardCharsets.UTF_8), "AES");
			GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

			cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
			byte[] decrypted = cipher.doFinal(encrypted);

			return new String(decrypted, StandardCharsets.UTF_8);
		}
		catch (Exception e) {
			throw new EncryptionException(ErrorCode.DECRYPTION_ERROR);
		}
	}

}
