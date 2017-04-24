package com.ronoel.decorateste.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * @author Ronoel Jr.
 */
public class CipherGaias {

    private static byte[] linebreak = {}; // Remove Base64 encoder default linebreak
    private static String secret = "alea6eofg45k53at"; // secret key length must be 16
    private static SecretKey key;
    private static Cipher cipher;
    private static Base64 coder;

    static {
        try {
            key = new SecretKeySpec(secret.getBytes(), "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            coder = new Base64(32, linebreak, true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static synchronized String encrypt(String plainText)
            throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return new String(coder.encode(cipherText));
    }
    
    public static synchronized String decrypt(String codedText)
            throws Exception {
        LOG.log(Level.INFO, "CodedText:{0}", codedText);
        byte[] encrypted = coder.decode(codedText.getBytes());
        LOG.log(Level.INFO, "Encrypted:{0}", encrypted);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted);
    }
    
    private static final Logger LOG = Logger.getLogger(CipherGaias.class.getName());

}
