package code.own.tongcheng58;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/14 20:44
 */
public class Main3 {
    public static void main(String[] args) {
        int[] nums= new int[]{1,-1,2,5,4};
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0 && nums[i] <= len) {
                res[nums[i] - 1] = 1;
            }
        }
        for (int i = 0; i < len; i++) {
            if (res[i] == 0) {
                System.out.println(i + 1);
            }
        }
        System.out.println(len +1);
    }

    private static void swap(int[] nums, int left, int i) {
        int tmp = nums[left];
        nums[left] = nums[i];
        nums[i] = tmp;
    }
}
