package Code.practice.huawei.niucoder.beginner;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/23 下午11:06
 * @description 按照指定的顺序进行排序
 */
public class HJ101 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = scanner.nextInt();
        }
        int sortFlag = scanner.nextInt();
        quickSort(nums, 0, nums.length - 1);
        printOut(sortFlag, nums);
    }

    public static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pivotPos = partition(nums, low, high);
            quickSort(nums, low, pivotPos - 1);
            quickSort(nums, pivotPos + 1, high);
        }
    }

    public static int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && pivot <= nums[high]) high--;
            nums[low] = nums[high];
            while (low < high && pivot >= nums[low]) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    public static void printOut(int sortFlag, int[] nums) {
        StringBuilder sb = new StringBuilder();
        if (0 == sortFlag) {
            for (int n : nums) {
                sb.append(n).append(" ");
            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                sb.append(nums[i]).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }
}
