package me.kazaff.tools.Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AES {

    public static byte[] generateSecret() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom());
        return keyGen.generateKey().getEncoded();
    }

    public static byte[] encrypt(byte[] content, byte[] secret) throws Exception{
        SecretKey key = new SecretKeySpec(secret, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(content);
    }

    public static byte[] decrypt(byte[] content, byte[] secret) throws Exception{
        SecretKey key = new SecretKeySpec(secret, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(content);
    }
}
