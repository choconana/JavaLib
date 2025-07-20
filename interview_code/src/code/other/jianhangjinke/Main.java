package code.other.jianhangjinke;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 11:01
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(delcoc(str));
    }

    private static String delcoc(String str) {
        if (str == null || str.length() == 0) return "";
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        String string = str.toUpperCase();
        int i = 0;
        while (i < len) {
            char c = string.charAt(i);
            if (c == 'C' && i < len - 2) {
                if (string.charAt(i+1) != 'O' || string.charAt(i+2) != 'C') {
                    sb.append(str.charAt(i));
                    i++;
                } else {
                    i += 3;
                }
            } else {
                sb.append(str.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
