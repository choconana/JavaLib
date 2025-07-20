package code.practice.huawei.niucoder.beginner;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author hezhidong
 * @date 2023/2/23 下午8:33
 * @description 提取不重复的整数
 */
public class HJ9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rowNum = scanner.nextLine();
        Set<Character> memo = new HashSet<>();
        char[] numChars = rowNum.toCharArray();
        char[] resChars = new char[numChars.length];
        int count = 0;
        for (int i = numChars.length - 1; i > 0; i--) {
            char c = numChars[i];
            if (!memo.contains(c)) {
                memo.add(c);
                resChars[count++] = c;
            }
        }
        System.out.println(String.valueOf(resChars));
    }
}
