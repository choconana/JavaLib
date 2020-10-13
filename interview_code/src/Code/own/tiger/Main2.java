package Code.own.tiger;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/20 16:58
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        helper(nums, n);
    }

    private static void helper(int[] nums, int n) {
        Arrays.sort(nums);
        for (int i = 1; i < n; i += 2) {
            if (i < n - 1) {
                int tmp = nums[i + 1];
                nums[i + 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
