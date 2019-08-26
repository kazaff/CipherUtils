# CipherUtils

本项目为基于AES和RSA对指定内容进行加解密的工具类。

# Encrypt/Decrypt

本工具提供的加/解密逻辑，分为两阶段：

1. 使用创建的随机的AES对称加密算法使用的key对目标字符串进行对称加密
2. 使用私钥对创建的随机key进行非对称加密

目的是为了解决RSA非对称加密算法对被加密数据的长度限制。`Cipher.encrypt`方法返回的`CipherData`对象包含上述两部分加密后的base64数据。

```java
import me.kazaff.tools.Cipher;
import me.kazaff.tools.Utils.KeyPairs;
import me.kazaff.tools.Utils.CipherData;

// 创建密钥对
KeyPairs keyPairs = Cipher.generateKeyPair();

// 获取私钥字符串, 用于交给通信接收方保存
String privKey = keyPairs.getPrivateKey();

// 获取公钥字符串, 用于交给通信请求方保存
String privKey = keyPairs.getPublicKey();

// 加密目标字符串
CipherData result = Cipher.encrypt("目标字符串", keyPairs.getPublicKey());
System.out.println(result); // aes_part对应对称加密后的base64数据，rsa_part对应非对称加密后的base64数据

// 解密数据
System.out.println(Cipher.decrypt(result, keyPairs.getPrivateKey()));
```


# Sign/Verify

非对称加密的性能不会很好，所以推荐使用非对称签名来保护通信双方的数据不被篡改。

```java
import me.kazaff.tools.Cipher;
import me.kazaff.tools.Utils.KeyPairs;

// 创建密钥对
KeyPairs keyPairs = Cipher.generateKeyPair();

// 获取私钥字符串, 用于交给通信请求方保存（注意这里和加解密是相反的，签名需要在请求方完成，所以请求方需要持有私钥）
String privKey = keyPairs.getPrivateKey();

// 获取公钥字符串, 用于交给通信接收方保存
String privKey = keyPairs.getPublicKey();

// 签名目标字符串
String signStr = Cipher.sign("目标字符串", keyPairs.getPrivateKey());
System.out.println(result); // 目标字符串的base64签名

// 校验签名
System.out.println(Cipher.verify("目标字符串", signStr, kp.getPublicKey()));

```
