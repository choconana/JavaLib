package Code.practice.huawei.niucoder.beginner;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/23 下午10:02
 * @description 输入n个数，输出其中最小的k个
 */
public class HJ58 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int topK = scanner.nextInt();
        int[] nums = new int[count];
        int point = 0;
        for (int i = 0; i < count; i++) {
            nums[point++] = scanner.nextInt();
        }
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < topK; i++) {
            sb.append(heap.poll()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
