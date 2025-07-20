package code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/24 下午9:05
 * @description 质数因子
 */
public class HJ6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sb.append(i).append(" ");
                num = num / i;
                i--;
            }
        }
        System.out.println(sb.append(num).toString());
    }
}
