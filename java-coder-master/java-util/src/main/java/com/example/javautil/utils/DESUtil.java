package com.example.javautil.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

@Slf4j
public class DESUtil {

    /**
     * @param datasource
     * @param password
     * @return
     */
    public static String desCrypto(String datasource, String password) {

        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂，然后用它把 DESKeySpec 转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher 对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化 Cipher 对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return new
                    String(Base64.encodeBase64(cipher.doFinal(datasource.getBytes("UTF-8"))));
        } catch (Throwable e) {
            log.error("desCrypto exception:{}", e.toString());
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src
     * @param password
     * @return
     * @throws Exception
     */
    public static String decrypt(String src, String password) throws Exception {

        // DES 算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个 DESKeySpec 对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将 DESKeySpec 对象转换成 SecretKey 对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher 对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化 Cipher 对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return new
                String(cipher.doFinal(Base64.decodeBase64(src.getBytes("UTF-8"))));
    }

}
