package Code.own.qunaer;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/23 19:57
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        String[] strings1 = scanner.nextLine().split(" ");
        String[] strings2 = scanner.nextLine().split(" ");
        System.out.println(longestCommenSeq(strings1, strings2));
    }

    private static int longestCommenSeq(String[] strings1, String[] strings2) {
        int len = strings1.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (strings1[i - 1].equals(strings2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len][len];

    }
}
