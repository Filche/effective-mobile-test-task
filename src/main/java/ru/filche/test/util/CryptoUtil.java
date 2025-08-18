package ru.filche.test.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class CryptoUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private final SecretKeySpec key;
    private final IvParameterSpec iv;

    public CryptoUtil() {
        try {
            String keyStr = "your256BitSecretKey1234567890123456";
            this.key = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), "AES");
            this.iv = new IvParameterSpec("randomInitVector".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Crypto setup failed", e);
        }
    }

    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] original = cipher.doFinal(decoded);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
