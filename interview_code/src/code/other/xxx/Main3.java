package code.other.xxx;

import java.util.Arrays;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/4 19:46
 */
public class Main3 {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
//        int m = 2;
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int m = 3;
        System.out.println(GetMaxConsecutiveOnes(nums, m));
    }
    public static int GetMaxConsecutiveOnes(int[] arr, int k) {
        int cur = 0;
        int record = 0;
        int tmpk = k;
        int len = arr.length;
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                record = i;
                break;
            }
        }
        while (cur < record) {
            k = tmpk;
            int[] nums = Arrays.copyOf(arr, len);
            for (int i = cur; i < len; i++) {
                if (k == 0) break;
                if (arr[i] == 0) {
                    nums[i] = 1;
                    k--;
                }
            }
            max = Math.max(max, helper(nums));
        }
        return max;
    }

    public static int helper(int[] nums) {
        int len = nums.length;
        int max = 0;
        int[] dp = new int[len];
        dp[0] = nums[0] == 1 ? 1 : 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 0;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
