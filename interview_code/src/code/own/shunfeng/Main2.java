package code.own.shunfeng;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 18:11
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine().toUpperCase();
        if (string == null || string.length() == 0) {
            System.out.println(0);
        } else {
            String[] strings = string.split(" ");
            System.out.println(res(strings));
        }
    }

    public static long res(String[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }
        long len = strings.length;
        long num = 0;
        for (int i = 0; i < len; i++) {
            if(strings[i].toUpperCase().equals("APPLE")) {
                num++;
            }
        }
        return num;
    }
}
