package me.kazaff.tools.Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static byte[] getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        return publicKey.getEncoded();
    }

    public static byte[] getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        return privateKey.getEncoded();
    }

    public static PublicKey getPublicKey(byte[] pubStr) throws Exception{
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(byte[]  priStr) throws Exception{
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(priStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }
}
