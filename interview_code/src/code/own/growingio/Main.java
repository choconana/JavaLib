package code.own.growingio;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/19 19:14
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(",");
        int len = strings.length;
        int amount = Integer.parseInt(strings[len - 1]);
        int[] coins = new int[len - 1];
        coins[0] = strings[0].charAt(1) - '0';
        coins[len - 2] = strings[len - 2].charAt(0) - '0';
        for (int i = 1; i < len - 2; i++) {
            coins[i] = Integer.parseInt(strings[i]);
        }
        System.out.println(change(coins, amount));
    }

    private static int change(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
