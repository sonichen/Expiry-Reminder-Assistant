package com.cyj.utils.wx;



import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * 微信工具类
 */
public class WechatDecryptDataUtil {

    public static void main(String[] args) {
        String result = decryptData(
                "cLX9mkn5ssDJ302WjqnuycJ9nhZGkogk0hTJgxrVWGhes191g5j8PBjtxq9sRhmwDZq7rHT7ctMG/hlsOuc7cAekwae2EIZoz/6IzBXiQCDfliwyu+NijxORFgqK2+fHVowAkqDzDbpCgsLPwKWjJC7vh+KTHXup6j2iSezThJw0tNXOMLkuqIKuZYzBCFAr3RbFpeixN6Vw/WFdXLXBc8s2LB7LoHaR9k9Zrx2yGaKQw7t1cGu1qbRC4Rzlwdz6j98HDatboFQ6Y/4seW8PQ5HZcN035U3B/5+uRK98PT7I/8XLr5c7xvUGamnD7dtD222t7ifycBgedAdh/BVPPQwuksp17uOVaD/IxQRaBusECHhIxybaFfl/5LLDRpZf3YVm/1yn0gEXLg98oeuk2VgtClcGxFEueO0nl+u3JkQ=",
                "d715EMyJK7/scZIvVi8E3w==",
                "Wntj7Vx3KoTpbAeNEMYn3A=="
        );
        System.out.println("result = " + result);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

}
