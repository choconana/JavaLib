package code.own.shunfeng;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxLen = 5;
        String str = scanner.nextLine();
        long len = str.length();
        String secstr = "";
        String[] strings = new String[2];
        strings[0] = "true";
        if (len >= maxLen) {
            strings[1] = str.substring(0, maxLen);
            secstr = str.substring(maxLen);
            System.out.println(strings[1]);
            System.out.println(secstr);
        } else {
            strings[1] = str;
        }
        while (strings[0].equals("true")) {
            String[] strs = helper(strings[1]);
            strings[0] = strs[0];
            StringBuffer sb = new StringBuffer(strs[1]);
            sb.append(secstr);
            String tmp = sb.toString();
            len = tmp.length();

            if (len >= maxLen) {
                strings[1] = tmp.substring(0, maxLen);
                secstr = tmp.substring(maxLen);
            } else {
                strings[1] = tmp;
            }
            System.out.println(strings[1]);
        }
        if (strings[1].length() == 0) {
            System.out.println("empty");
        } else {
            System.out.println(strings[1]);
        }
    }

    public static String[] helper(String str) {
        String[] strings = new String[2];
        strings[0] = "false";
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean flag = true;
        while (i < len) {
            if (flag && i < len - 1 && str.charAt(i) == str.charAt(i + 1)) {
                i += 2;
                strings[0] = "true";
                flag = !flag;
            } else {
                sb.append(str.charAt(i));
                i++;
            }
        }
        strings[1] = sb.toString();
        return strings;
    }
}
/*
XXYXXYXXYXXYXXYXXXYXXYX
*/