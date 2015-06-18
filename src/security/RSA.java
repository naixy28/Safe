package security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class RSA {
    public static KeyPair generateKP(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPublicKey(KeyPair kp){
        RSAPublicKey rsaPublicKey = (RSAPublicKey)kp.getPublic();
        return new String(Base64.encodeBase64(rsaPublicKey.getEncoded()));
    }

    public static String getPrivateKey(KeyPair kp){
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)kp.getPrivate();
        return new String(Base64.encodeBase64(rsaPrivateKey.getEncoded()));
    }

    public static byte[] decode(String key){
        return Base64.decodeBase64(key.getBytes());
    }


    public static byte[] encrypt(byte[] rsaPublicKey,String src){
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            return cipher.doFinal(src.getBytes());
        } catch (Exception e) {
            return null;
        }

    }

    public static String decrypt(byte[] rsaPrivateKey,byte[] src){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] result = cipher.doFinal(src);
            return new String(result);
        } catch (Exception e) {
            return null;
        }
    }


}
