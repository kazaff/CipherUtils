package me.kazaff.tools;

import me.kazaff.tools.Utils.AES;
import me.kazaff.tools.Utils.CipherData;
import me.kazaff.tools.Utils.KeyPairs;
import me.kazaff.tools.Utils.RSA;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Cipher {
    public static KeyPairs generateKeyPair() throws Exception{
        // 创建RSA非对称加密的密钥对
        KeyPair keyPair = RSA.generateKeyPair();
        BASE64Encoder encoder = new BASE64Encoder();

        KeyPairs keyPairs = new KeyPairs();
        keyPairs.setPrivateKey(encoder.encode(RSA.getPrivateKey(keyPair)).replaceAll("\r\n", ""));
        keyPairs.setPublicKey(encoder.encode(RSA.getPublicKey(keyPair)).replaceAll("\r\n", ""));

        return keyPairs;
    }

    public static byte[] generateSecret() throws Exception{
        // 创建用于AES对称加密的密钥
        return AES.generateSecret();
    }

    public static CipherData encrypt(String content, String pubKey) throws Exception{
        CipherData result = new CipherData();
        BASE64Encoder encoder = new BASE64Encoder();

        // 生成 AES 对称加密使用的 secret
        byte[] secret = generateSecret();
        // 使用 secret 对 Content 进行 AES 对称加密
        result.setAesPart(
                encoder.encode(
                        AES.encrypt(content.getBytes(UTF_8), secret)
                ).replaceAll("\r\n", "")
        );

        // 使用 pubKey 对 secret 进行 RSA 非对称加密
        BASE64Decoder decoder = new BASE64Decoder();
        result.setRsaPart(
                encoder.encode(
                        RSA.encrypt(
                                secret,
                                RSA.getPublicKey(decoder.decodeBuffer(pubKey))
                        )
                ).replaceAll("\r\n", "")
        );

        return result;
    }

    public static String decrypt(CipherData content, String privKey) throws Exception{
        BASE64Decoder decoder = new BASE64Decoder();

        // 使用 privKey 对 content.rsa_part 进行解密，得到 secret
        byte[] secret = RSA.decrypt(decoder.decodeBuffer(content.getRsaPart()), RSA.getPrivateKey(decoder.decodeBuffer(privKey)));

        // 使用 secret 对 content.aes_part 进行解密，得到原始数据并返回
        byte[] result = AES.decrypt(decoder.decodeBuffer(content.getAesPart()), secret);

        return new String(result, UTF_8);
    }
}
