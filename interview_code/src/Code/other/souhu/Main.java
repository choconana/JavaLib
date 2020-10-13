package Code.other.souhu;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/30 17:26
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(helper(string));
    }

    public static boolean helper(String string) {
        if (string.length() == 0) {
            return true;
        }
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < string.length(); i++) {
            char t = string.charAt(i);
            if ( a < 0 || b < 0 || c < 0) return false;
            switch (t) {
                case '(':
                    a++;
                    break;
                case ')':
                    a--;
                    break;
                case '[':
                    b++;
                    break;
                case ']':
                    b--;
                    break;
                case '{':
                    c++;
                    break;
                case '}':
                    c--;
                    break;
            }

        }
        if (a != 0 || b != 0 || c != 0) {
            return false;
        } else {
            return true;
        }
    }
}
