package com.commonlib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法工具类
 * MD5-16位加密 MD5-32位加密 SHA256加密
 */
public class EncryptUtils {
    /**
     * md5 16位 加密
     *
     * @param plainText
     * @return
     */
    public static String MD5_16(String plainText) {
        StringBuffer buf = encrypt(plainText, "MD5");
        return buf.toString().substring(8, 24);
    }

    /**
     * md5 32位 加密
     *
     * @param plainText
     */
    public static String MD5_32(String plainText) {
        StringBuffer buf = encrypt(plainText, "MD5");
        return buf.toString();
    }

    /**
     * SHA-256加密
     *
     * @param plainText
     * @return
     */
    public static String SHA256(String plainText) {
        StringBuffer buf = encrypt(plainText, "SHA-256");
        return buf.toString();
    }

    private static StringBuffer encrypt(String text, String type) {
        StringBuffer buff = new StringBuffer("");
        int i;
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            md.update(text.getBytes());
            byte b[] = md.digest();

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buff.append("0");
                buff.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e("EncryptUtils", e.getMessage());
        }
        return buff;
    }
}
