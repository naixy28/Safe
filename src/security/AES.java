package security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


/**
 * Created by 电脑 on 2015/6/17.
 * ...
 */
public class AES {
    public static String createAESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return new String(Base64.encodeBase64(secretKey.getEncoded()));
        } catch (Exception e) {
            return null;
        }
    }

    public static Key parseKey(String aeskey){
        byte[] aseKeyBytes = Base64.decodeBase64(aeskey.getBytes());
        return new SecretKeySpec(aseKeyBytes, "AES");
    }

    private Key AESKey;
    public AES(Key key) {
        this.AESKey = key;
    }

    public byte[] encrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AESKey);
            return cipher.doFinal(src.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    public String decrypt(byte[] code){
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, AESKey);
            return new String(cipher.doFinal(code));
        } catch (Exception e) {
            return null;
        }
    }
}

