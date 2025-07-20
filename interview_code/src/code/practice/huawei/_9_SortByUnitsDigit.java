package code.practice.huawei;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/3/5 上午6:51
 * @description 整型数组按个位值排序
 */
public class _9_SortByUnitsDigit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nums = scanner.nextLine().split(",");
        bubbleSort(nums);
        print(nums);
    }

    public static void bubbleSort(String[] nums) {
        int len = nums.length;
        boolean flag;
        for (int i = 0; i < len; i++) {
            flag = false;
            for (int j = len - 1; j > i; j--) {
                if (compareTo(nums[j - 1], nums[j]) > 0) {
                    String tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }

                if (flag) {
                    return;
                }
            }
        }
    }


    public static int compareTo(String s1, String s2) {
        return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
    }

    public static void print(String[] nums) {
        System.out.println(String.join(",", nums));
    }
}
