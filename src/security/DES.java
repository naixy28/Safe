package security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;


/**
 * Created by 电脑 on 2015/6/17.
 * ...
 */
public class DES {
    public static String createDESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            return new String(Base64.encodeBase64(secretKey.getEncoded()));
        } catch (Exception e) {
            return null;
        }
    }

    private Key DESKey;
    public DES(Key key) {
        this.DESKey = key;
    }

    public byte[] encrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, DESKey);
            return cipher.doFinal(src.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    public String decrypt(byte[] code){
        try{
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, DESKey);
            return new String(cipher.doFinal(code));
        } catch (Exception e) {
            return null;
        }
    }

    public static Key parseKey(String text) {
        //key转换
        try {
            DESKeySpec desKeySpec =  new DESKeySpec(Base64.decodeBase64(text.getBytes()));
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            return factory.generateSecret(desKeySpec);
        } catch (Exception e) {
            return null;
        }
    }
}

