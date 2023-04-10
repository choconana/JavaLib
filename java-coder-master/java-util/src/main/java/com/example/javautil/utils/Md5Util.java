package com.example.javautil.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * md5 加密
 */
@Slf4j
public class Md5Util {

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
            "b", "c", "d", "e", "f"};

    /**
     * MD5加密
     *
     * @param origin      字符
     * @param charsetname 编码
     */
    public static String MD5Encode(String origin, String charsetname) {

        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
            log.error("md5 encode exception:{}", e.toString());
        }
        return resultString;
    }


    /**
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte b[]) {

        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * @param b
     * @return
     */
    public static String byteToHexString(byte b) {

        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }


}
