package leetcode;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/21 0:46
 */
public class LCS {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s1 = scn.nextLine();
        String s2 = scn.nextLine();
        LCS(s1.toCharArray(), s2.toCharArray());
    }

    public static void LCS(char[] c1, char[] c2) {
        // 1. 定义状态数组
        StringBuilder[][] dp = new StringBuilder[c1.length + 1][c2.length + 1];

        // 2. 初始化dp数据
        for (int i = 0; i <= c1.length; i++) {
            for (int j = 0; j <= c2.length; j++) {
                dp[i][j] = new StringBuilder("");
            }
        }

        for(int i = 0; i < c1.length; i++) {
            for(int j = 0; j < c2.length; j++) {
                // 3. 定义状态函数
                if(c1[i] == c2[j]) {
                    dp[i + 1][j + 1] = dp[i][j].append(c1[i]);
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j].length() < dp[i][j + 1].length() ?
                            new StringBuilder(dp[i][j + 1]) : new StringBuilder(dp[i + 1][j]);
                }
            }
        }
        System.out.println(dp[c1.length][c2.length]);
    }
}
