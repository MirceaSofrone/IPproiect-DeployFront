package com.hpprediction.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encoder {

    private static SecretKeySpec secretKey;
    private static final String SECRET="house";
    private static final  Logger LOGGER = LoggerFactory
            .getLogger(Encoder.class);

    private Encoder(){
        throw new IllegalStateException("Utility class");
    }

    public static void setKey(String myKey) {
        MessageDigest sha;
        try {
            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-512");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(String.valueOf(e));
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            setKey(SECRET);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            return "No algorithm for encode";
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            setKey(SECRET);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            return "No algorithm for decode";
        }
    }
}
