package security;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class MD5 {
    public static String createMD5(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(src.getBytes("UTF8"));
            return new String(Hex.encodeHex(md5Bytes));
        } catch (Exception e) {
            return null;
        }
    }
}
