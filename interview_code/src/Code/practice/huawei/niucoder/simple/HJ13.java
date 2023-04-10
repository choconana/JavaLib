package Code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/25 下午3:45
 * @description 句子逆序
 */
public class HJ13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strArr = scanner.nextLine().split(" ");
        int arrLen = strArr.length;
        String tmp;
        for (int i = 0; i < arrLen / 2; i++) {
            tmp = strArr[i];
            strArr[i] = strArr[arrLen - i - 1];
            strArr[arrLen - i - 1] = tmp;
        }
        System.out.println(String.join(" ", strArr));
    }
}
