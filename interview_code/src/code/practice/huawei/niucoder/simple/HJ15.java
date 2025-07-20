package code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/25 下午6:49
 * @description 求int型正整数在内存中存储时1的个数
 */
public class HJ15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int count = 0;
        while (num > 0) {
            if (1 == num % 2) {
                count++;
            }
            num /= 2;
        }
        System.out.println(count);
    }
}
