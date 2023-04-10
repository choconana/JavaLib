package Code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/24 下午3:48
 * @description 字符串分隔
 */
public class HJ4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawStr = scanner.nextLine();

        if (rawStr.length() == 0) {
            System.out.println("00000000");
            return;
        }
        String paddingStr = pad(rawStr);
        String[] strs = split(paddingStr);
        printOut(strs);

    }

    public static String pad(String rawStr) {
        int mod;
        if ((mod = rawStr.length() % 8) == 0) {
            return rawStr;
        }
        int padding = 8 - mod;
        StringBuilder sb = new StringBuilder(rawStr);
        for (int i = 0; i < padding; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static String[] split(String str) {
        int count = str.length() / 8;
        String[] strs = new String[count];
        for (int i = 0; i < count; i++) {
            strs[i] = str.substring(i * 8, (i + 1) * 8);
        }
        return strs;
    }

    public static void printOut(String[] strs) {
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
