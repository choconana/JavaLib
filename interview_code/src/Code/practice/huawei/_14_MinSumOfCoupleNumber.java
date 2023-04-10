package Code.practice.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/3/6 下午12:07
 * @description 整数对最小和
 */
public class _14_MinSumOfCoupleNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int len1 = scanner.nextInt();
        int[] array1 = new int[len1];
        for (int i = 0; i < len1; i++) {
            array1[i] = scanner.nextInt();
        }

        int len2 = scanner.nextInt();
        int[] array2 = new int[len2];
        for (int i = 0; i < len2; i++) {
            array2[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();

        int[] sumArray = new int[len1 * len2];
        int count = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                sumArray[count++] = array1[i] + array2[j];
            }
        }

        Arrays.sort(sumArray);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += sumArray[i];
        }
        System.out.println(sum);
    }
}
