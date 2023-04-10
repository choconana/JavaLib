package Code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/24 下午1:44
 * @description 计算某字符出现的次数
 */
public class HJ2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toLowerCase();
        char target = scanner.next().toLowerCase().charAt(0);
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (c == target) {
                count++;
            }
        }
        System.out.println(count);
    }
}
