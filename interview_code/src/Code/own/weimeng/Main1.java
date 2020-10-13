package Code.own.weimeng;

import java.util.Arrays;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/10/11 16:30
 */
public class Main1 {
    public static void main(String[] args) {
        System.out.println(getPayCount(3));
    }
    public static int getPayCount (int n) {
        // write code here
        int[] coins = new int[]{1,2,5,10};
        int max = n + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }
}
