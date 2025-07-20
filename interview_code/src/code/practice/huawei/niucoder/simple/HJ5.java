package code.practice.huawei.niucoder.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/24 下午5:55
 * @description 16进制转10进制
 */
public class HJ5 {

    final static Map<Character, Integer> hex2TenMap = new HashMap<Character, Integer>() {
        {
            put('0', 0);
            put('1', 1);
            put('2', 2);
            put('3', 3);
            put('4', 4);
            put('5', 5);
            put('6', 6);
            put('7', 7);
            put('8', 8);
            put('9', 9);
            put('A', 10);
            put('B', 11);
            put('C', 12);
            put('D', 13);
            put('E', 14);
            put('F', 15);
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawHex = scanner.next();
        String hexNum = rawHex.substring(2);
        char[] hexChar = hexNum.toCharArray();
        int charLen = hexChar.length;
        double normalNum = 0;
        for (int i = charLen - 1; i >= 0; i--) {
            normalNum += hex2TenMap.get(hexChar[i]) * Math.pow(16, charLen - i - 1);
        }
        System.out.println((int) normalNum);
    }
}