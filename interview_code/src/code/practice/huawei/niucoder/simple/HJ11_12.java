package code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/25 下午3:19
 * @description 数字颠倒，逆序输出
 */
public class HJ11_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] num = scanner.next().toCharArray();
        int len = num.length;
        char tmp;
        for (int i = 0; i < len / 2; i++) {
            tmp = num[i];
            num[i] = num[len - i - 1];
            num[len - i - 1] = tmp;
        }
        System.out.printf(String.valueOf(num));
    }
}
