package code.own.sohu;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        int c = 3;
        System.out.println(helper(a, b, c));
    }

    private static int helper(int a, int b, int c) {
        int[] nums = new int[3];
        int count = 0;
        nums[0] = a;
        nums[1] = b;
        nums[2] = c;
        Arrays.sort(nums);
        int tmp = nums[0];
        for (int i = 0; i < 3; i++) {
            nums[i] = nums[i] - tmp;
        }
        count = tmp;
        if (nums[1] > 1) {
            while (nums[1] > 1) {
                nums[1] -= 2;
                nums[2] -= 2;
                count++;
            }
            if (nums[2] >= 3) {
                count++;
                nums[2] -= 2;
                while (nums[2] >= 5) {
                    nums[2] -= 5;
                    count++;
                }
            }
        } else if (nums[1] == 0) {
            count = tmp;
            while (nums[2] >= 5) {
                nums[2] -= 5;
                count++;
            }
        }
        return count;
    }
}
