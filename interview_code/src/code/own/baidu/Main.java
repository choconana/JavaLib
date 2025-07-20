package code.own.baidu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] num = new int[100000];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] strings = scanner.nextLine().split(" ");
        helper(strings);
    }

    private static void helper(String[] strings) {
        Arrays.sort(strings, Comparator.reverseOrder());
        long num = 0;
        int record = 0;
        boolean flag = false;
        int len = strings.length - 1;
        int result = 0;
        for (int i = 0; i <= len; i++) {
            if (strings[i].equals("0")) {
                record = i;
                break;
            }
        }
        for (int k = 0; k <= len ; k++) {
            String[] str = new String[len - k + 1];
            int count = 0;
            for (int i = k; i <= len; i++) {
                str[count++] = strings[i];
            }
            record--;
            for (int i = record; i <= len; i++) {
                for (int j = record; j > 0; j--) {
                    if (j != record) {
                        swap(str, j, j - 1);
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String s : str) {
                        sb.append(s);
                    }
                    num = Long.parseLong(sb.toString());
                    if (num % 90 == 0) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
            if (flag) break;

        }
        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(num);
        }
    }


    public static void swap(String[] strings, int i, int j) {
        String tmp = strings[i];
        strings[i] = strings[j];
        strings[j] = tmp;
    }
}
