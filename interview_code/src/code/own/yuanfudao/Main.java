package code.own.yuanfudao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int count = 0;
        int[] nums = new int[n];
        while (count < n) {
            nums[count++] = scanner.nextInt();
        }
        int[] res = helper(nums, m);
        for (int i = 0; i < n; i++) {
            System.out.print(res[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    private static int[] helper(int[] nums, int m) {
        int len = nums.length;
        int[] res = new int[len];
        int[] tmp = nums;
        while (m > 0) {
            res = staggerPoker(tmp);
            tmp = res;
            m--;
        }

        return res;
    }

    private static int[] staggerPoker(int[] tmp) {
        int len = tmp.length;
        int[] res = new int[len];
        for (int i = 0; i < len / 2; i++) {
            res[2 * i + 1] = tmp[i];
            res[2 * i] = tmp[i + len / 2];
        }
        if (len % 2 == 1) {
            res[len - 1] = tmp[len - 1];
        }
        return res;
    }
}

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/12 19:47
 */
/*
7 1
3 1 4 2 5 6 7
*/
