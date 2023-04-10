package Code.practice.huawei;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/3/5 上午5:22
 * @description 数组连续和
 */
public class _7_ArrayContinuousSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int x = scanner.nextInt();
        int[] numArray = new int[N];
        for (int i = 0; i < N; i++) {
            numArray[i] = scanner.nextInt();
        }

        int left = 0;
        int right = 0;
        int sum = numArray[left];
        int count = 0;
        while (left <= right && right < N) {
            if (sum >= x) {
                count += N - right;
                if (left == right) {
                    right++;
                    if (right < N) {
                        sum += numArray[right];
                    }
                }
                sum -= numArray[left];
                left++;
            } else {
                right++;
                if (right < N) {
                    sum += numArray[right];
                }
            }
        }
        System.out.println(count);
    }
}
