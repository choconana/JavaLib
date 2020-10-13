package Code.own.huawei;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] nums2 = new int[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                nums2[i][j] = scanner.nextInt();
            }
        }
        scanner.nextLine();
        int m = Integer.parseInt(scanner.nextLine());
        int[][] nums1 = new int[2][m];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                nums1[i][j] = scanner.nextInt();
            }
        }
        System.out.println(KMP(nums1, nums2));
    }

    private static int KMP(int[][] nums1, int[][] nums2) {
        int len2 = nums2[0].length;
        int[] next = next(nums2[0]);
        int i = 0;
        int j = 0;
        int cur = 0;
        int res = 0;
        boolean flag = true;
        for (;;) {
            flag = true;
            j = 0;
            while (i <= nums1[0].length - 1 && j <= len2 - 1) {
                if (j == -1 || nums1[0][i] == nums2[0][j]) {
                    i++;
                    j++;
                } else {
                    j = next[j];
                }
            }
            if (j == len2) {
                res = i - len2;
                cur = res;
                int post1 = cur + len2 - 1;
                int post2 = len2 - 1;
                for (int k = 0; k < len2 / 2 + 1; k++) {
                    if (nums1[1][cur++] != nums2[1][k]) {
                        flag = false;
                        break;
                    }
                    if (nums1[1][post1--] != nums2[1][post2--]) {
                        flag = false;
                        break;
                    }

                }
            } else {
                flag = false;
            }
            if (flag) break;
            if (i == nums1[0].length) break;
        }
        if (flag) {
            return res + 1;
        } else {
            return 0;
        }
    }

    private static int[] next(int[] nums2) {
        int[] next = new int[nums2.length];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < nums2.length - 1) {
            if (j == -1 || nums2[i] == nums2[j]) {
                i++;
                j++;
                if (nums2[i] != nums2[j]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
/*
5
1 2 3 3 1
3 2 1 1 3
10
1 2 1 2 3 1 2 3 3 1
5 4 3 2 1 3 2 1 1 1
*/
