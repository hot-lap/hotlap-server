package com.oops.common.encrypt;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor {

	private final String key;

	private final String algorithm;

	private final ObjectMapper mapper = new ObjectMapper();

	public Encryptor(String key, String algorithm) {
		this.key = key;
		this.algorithm = algorithm;
	}

	public <T> T dec(String encData, Class<T> clazz) throws Exception {
		String decryptedData = decrypt(encData);
		return mapper.readValue(decryptedData, clazz);
	}

	public <T> EncryptData enc(T plainData) throws Exception {
		String stringPlainData = mapper.writeValueAsString(plainData);
		return new EncryptData(encrypt(stringPlainData));
	}

	public String encrypt(String text) throws Exception {
		Cipher cipher = cipher(Cipher.ENCRYPT_MODE);
		byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String text) throws Exception {
		Cipher cipher = cipher(Cipher.DECRYPT_MODE);
		byte[] decodedBytes = Base64.getDecoder().decode(text);
		byte[] decrypted = cipher.doFinal(decodedBytes);
		return new String(decrypted, StandardCharsets.UTF_8);
	}

	private Cipher cipher(int mode) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));

		cipher.init(mode, keySpec, ivParamSpec);
		return cipher;
	}

}
