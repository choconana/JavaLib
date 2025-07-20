package code.own.xunfei;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean flag = true;
        int pre = 0;
        int post = 0;
        ArrayList<Character> string = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '_') {
                pre = i;
                break;
            }
        }
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) != '_') {
                post = i;
                break;
            }
        }
        String s1 = s.substring(pre, post + 1);
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (flag || c != '_') {
                string.add(c);
            }
            if (c == '_') {
                flag = false;
            } else {
                flag = true;
            }
        }
        for (char c : string) {
            System.out.print(c);
        }
    }
}
