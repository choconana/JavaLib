package code.anonym;

public class Test {
    public static void main(String[] args) {
        int[] nums = new int[6];
        nums[0] = 3;
        nums[1] = 2;
        nums[2] = 1;
        nums[3] = 5;
        nums[4] = 6;
        nums[5] = 4;
        System.out.println(findKthLargest(nums, 2));
    }
    public static int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pos = partition(nums, left, right);
            quickSort(nums, left, pos - 1);
            quickSort(nums, pos + 1, right);
        }
    }

    public static int partition(int[] nums, int low, int high) {
        int base = nums[low];
        while (low < high) {
            while (low < high && base <= nums[high]) high--;
            nums[low] = nums[high];
            while (low < high && base >= nums[low]) low++;
            nums[high] = nums[low];
        }
        nums[low] = base;
        return low;
    }
}
