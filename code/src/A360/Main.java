package A360;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/22 20:06
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<String> list = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < num; i++) {
            String str = scanner.nextLine();
            list.add(str);
        }
        validation(list);
    }

    public static void validation(List<String> list) {
        int count = 0;
        for (String str : list) {
            if (str.length() <= 10) {
                char[] c = str.toUpperCase().toCharArray();
                if (helper(c)) count++;
            }
        }
        System.out.println(count);
    }

    public static boolean helper(char[] c) {
        for (char c1 : c) {
            if (c1 > 90 || c1 < 65) {
                return false;
            }
        }
        return true;
    }
}
