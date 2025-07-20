package code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/24 下午1:18
 * @description 字符串最后一个单词的长度
 */
public class HJ1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String words = scanner.nextLine();
        String[] wordArray = words.split(" ");
        if (0 == wordArray.length) {
            System.out.println(0);
        }
        System.out.println(wordArray[wordArray.length - 1].length());
    }
}
