package Code.other.qianxin;

import java.util.ArrayList;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/2 16:14
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(3);
        nums.add(2);
        nums.add(4);
        nums.add(5);
        nums.add(4);
        nums.add(3);
        nums.add(2);
        nums.add(1);

//        nums.add(4);
//        nums.add(2);
//        nums.add(3);

//        nums.add(4);
//        nums.add(1);
//        nums.add(3);
//        nums.add(3);
//        nums.add(3);

        helper(nums);
    }

    public static void helper(ArrayList<Integer> nums) {
        int len = nums.size();
        int[] dp = new int[len];
        int sum = 0;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                dp[i] = dp[i - 1] + 1;
            } else if(nums.get(i - 1) == nums.get(i)) {
                dp[i] = 1;
            } else {
                dp[i] = 1;
                for (int j = i; j >= 1; j--) {
                    if (j <= 1 || (nums.get(j - 1) > nums.get(j) && dp[j - 1] <= dp[j])) {
                        dp[j - 1] += 1;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int n : dp) {
            sum += n;
        }

//        int n = nums.size();
//        int sum = n;
//        int cur = 1;
//        sum += nums.get(1) < nums.get(0) ? 1 : 0;
//        for (int i = 1; i < n; i++) {
//            if (nums.get(i) > nums.get(i - 1)) {
//                cur++;
//                sum += cur - 1;
//            } else {
//                cur = 1;
//            }
//        }
        System.out.println(sum);
    }
}
