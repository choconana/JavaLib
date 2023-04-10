package com.example.javautil.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {

        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isPositiveDecimal(String orginal) {
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
    }

    /**
     * 整数或者小数
     *
     * @param orginal
     * @return
     */
    public static boolean isIntOrDecimal(String orginal) {
        return isMatch("^[+-]?\\d+(\\.\\d+)?$", orginal);
    }

    public static String getNumberFromString(String s) {
        Pattern pattern = Pattern.compile("[^0-9.]");
        return pattern.matcher(s).replaceAll("").trim();
    }

    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

}
