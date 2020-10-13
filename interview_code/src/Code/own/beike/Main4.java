package Code.own.beike;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] a = new int[2 * n];
        int[] b = new int[2 * n];
        int count1 = 0, count2 = 0;
        String[] strings = scanner.nextLine().split(" ");
        for (String s : strings) {
            a[count1++] = Integer.parseInt(s);
        }
        strings = scanner.nextLine().split(" ");
        for (String s : strings) {
            b[count2++] = Integer.parseInt(s);
        }
        System.out.println(helper(2 * n, a, b));
    }

    private static int helper(int len, int[] a, int[] b) {
        int left = (len - 1) / 2, right = (len - 1) / 2 + 1;
        int[] dp = new int[len];
        dp[left] = b[left] - a[left];
        dp[right] = b[right] - a[right];
        int min = dp[left--] + dp[right++];
        int tmp = 0;
        while (left >= 0 && right < len) {
            dp[left] = b[left] - a[left];
            dp[right] = b[right] - a[right];
            tmp = dp[left] + dp[right];
            min = Math.min(min, min + tmp);
            left--;
            right++;
        }
        if (min <= 0) {
            min = -min + 1;
        } else {
            min = 0;
        }
        return min;
    }
}
