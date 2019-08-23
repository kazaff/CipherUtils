package me.kazaff.tools;

import me.kazaff.tools.Utils.AES;
import me.kazaff.tools.Utils.CipherData;
import me.kazaff.tools.Utils.KeyPairs;
import me.kazaff.tools.Utils.RSA;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
//        KeyPairs keyPairs = Cipher.generateKeyPair();
//
//        CipherData result = Cipher.encrypt("中文真棒", keyPairs.getPublicKey());
//        System.out.println(result.getAesPart());
//        System.out.println(result.getRsaPart());
//
//        System.out.println(Cipher.decrypt(result, keyPairs.getPrivateKey()));


        KeyPairs kp = Cipher.generateKeyPair();
        long startTime = System.nanoTime();
        String signStr = Cipher.sign("kazaff", kp.getPrivateKey());
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("RSA Sign Execution time in milliseconds : " +
                timeElapsed / 1000000);
        System.out.println(signStr);

        startTime = System.nanoTime();
        System.out.println(Cipher.verify("kazaff", signStr, kp.getPublicKey()));
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("RSA Verify Execution time in milliseconds : " +
                timeElapsed / 1000000);

//        String content = "kazaffisgoodman";
//        System.out.println("original content: " + content);
//        System.out.println("--------------------------");
//
//        KeyPair kp = RSA.generateKeyPair();
//        BASE64Encoder encoder = new BASE64Encoder();
//
//        long startTime = System.nanoTime();
//        byte[] resultRSA = RSA.encrypt(content.getBytes(UTF_8), kp.getPublic());
//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println("RSA Encryption Execution time in milliseconds : " +
//                timeElapsed / 1000000);
//        System.out.println("--------------------------");
//        System.out.println("RSA cipher data: \r\n" + encoder.encode(resultRSA));
//        System.out.println("--------------------------");
//
//        byte[] secret = AES.generateSecret();
//        startTime = System.nanoTime();
//        byte[] resultAES = AES.encrypt(content.getBytes(UTF_8), secret);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("AES Encryption Execution time in milliseconds : " +
//                timeElapsed / 1000000);
//        System.out.println("--------------------------");
//        System.out.println("AES cipher data: \r\n" + encoder.encode(resultAES));
//
//        System.out.println("++++++++++++++++++++++++++");
//
//        startTime = System.nanoTime();
//        byte[] plainRSA = RSA.decrypt(resultRSA, kp.getPrivate());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("RSA Decryption Execution time in milliseconds : " +
//                timeElapsed / 1000000);
//        System.out.println("--------------------------");
//        System.out.println("RSA plain data: " + new String(plainRSA, UTF_8));
//
//        System.out.println("--------------------------");
//
//        startTime = System.nanoTime();
//        byte[] plainAES = AES.decrypt(resultAES, secret);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("AES Decryption Execution time in milliseconds : " +
//                timeElapsed / 1000000);
//        System.out.println("--------------------------");
//        System.out.println("RSA plain data: " + new String(plainAES, UTF_8));
    }
}
