package code.practice.huawei.niucoder.simple;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author hezhidong
 * @date 2023/2/25 下午2:52
 * @description 字符个数统计
 */
public class HJ10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.nextLine().toCharArray();
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : chars) {
            if (!set.contains(c)) {
                set.add(c);
                count++;
            }
        }
        System.out.println(count);
    }
}
