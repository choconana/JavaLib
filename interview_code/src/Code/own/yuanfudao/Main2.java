package Code.own.yuanfudao;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            String str = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (str.charAt(j) == '(') {
                    count++;
                    if (j < str.length() - 1) {
                        char ch = str.charAt(j + 1);
                        if (ch != '+' && ch != '-' && ch != '*') {
                            sb.append("invalid");
                            break;
                        }
                    }
                } else if (str.charAt(j) == ')') {
                    count--;
                } else if (str.charAt(j) != ' ') {
                    sb.append(c);
                }
            }
            if (count == 0) {
                strings.add(sb.toString());
            } else {
                strings.add("invalid");
            }
        }
        for (String s : strings) {
            String[] strs = s.split("");
            System.out.println(calculator(strs));
        }
    }

    private static String calculator(String[] s) {
        if (s[0].equals("i")) {
            return "invalid";
        }
        return "";
    }
}
/*
4
(- 0 1)
(+ 2 20)
+  1 2)
(  2 2)
*/
