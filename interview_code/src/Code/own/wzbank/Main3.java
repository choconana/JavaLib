package Code.own.wzbank;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/27 19:45
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = scanner.nextLine().split(" ");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        System.out.println(triGroup(n, nums));
    }

    private static int triGroup(int n, int[] nums) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                if (nums[i] <= nums[j]) {
                    for (int k = j + 1; k < len; k++) {
                        if (nums[j] <= nums[k]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
