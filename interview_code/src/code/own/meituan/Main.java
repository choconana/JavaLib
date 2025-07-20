package code.own.meituan;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int n = Integer.parseInt(str);
        str = scanner.nextLine();
        helper(n, str);
    }

    public static void helper(int n, String str) {
        if (n < 4) {
            System.out.println("");
            return;
        }
        int mPre = -1;
        int tPre = 0;
        int mPost = 0;
        int tPost = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'M') {
                mPre = i;
                break;
            }
        }
        for (int i = mPre; i < str.length(); i++) {
            if (str.charAt(i) == 'T') {
                tPre = i;
                break;
            }
        }
        for (int i = str.length() - 1; i > tPre; i--) {
            if (str.charAt(i) == 'T') {
                tPost = i;
                break;
            }
        }
        for (int i = tPost; i > tPre; i--) {
            if (str.charAt(i) == 'M') {
                mPost = i;
                break;
            }
        }
        if (mPre >=0 && tPre > mPre && mPost > tPre && tPost > mPost) {
            System.out.println(str.substring(tPre + 1, mPost));
        } else {
            System.out.println("");
        }
    }
}
