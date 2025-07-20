package code.own.A360;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/24 13:45
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = new String[10];
        int n = 0;
        String str = "";
        strings[n++] = scanner.nextLine();
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (str.equals("")) break;
            strings[n++] = str;
        }
        hepler(strings);
    }

    public static void hepler(String[] strings) {
        int len = 0;
        boolean flag = false;
        for (String str : strings) {
            if (str == null) break;
            len = str.length();
            char[] c = str.toCharArray();
            for (int i = 0; i < len / 2; i++) {
                if (c[i] == c[len - i - 1]) {
                    flag = validation(c[i]);
                } else {
                    flag = false;
                }
            }
            if (flag) {
                if (len % 2 == 0) {
                    System.out.println("YES");
                } else {
                    if (validation(c[len / 2])) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
            } else {
                System.out.println("NO");
            }

        }
    }

    public static boolean validation(char c) {
        if (c == 'A') {
            return true;
        } else if (c == 'H') {
            return true;
        } else if (c == 'I') {
            return true;
        } else if (c == 'M') {
            return true;
        } else if (c == 'O') {
            return true;
        } else if (c == 'T') {
            return true;
        } else if (c == 'U') {
            return true;
        } else if (c == 'V') {
            return true;
        } else if (c == 'W') {
            return true;
        } else if (c == 'X') {
            return true;
        } else if (c == 'Y') {
            return true;
        } else {
            return false;
        }
    }

}
