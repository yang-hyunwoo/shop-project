package shop.project.mall.util.encoder;

import shop.project.mall.exception.CustomApiException;
import shop.project.mall.exception.error.ErrorCode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Aes256Util {

    public static String alg = "AES/CBC/PKCS5Padding";


    public String encrypt(String key ,String text){
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            String iv = key.substring(0, 16); // 16byte
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomApiException(ErrorCode.ENCRYPTION_ERROR.getMessage());
        }
    }

    public String decrypt(String key ,String cipherText)  {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            String iv = key.substring(0, 16); // 16byte
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e){
            throw new CustomApiException(ErrorCode.DECODE_ERROR.getMessage());
        }
    }
}
