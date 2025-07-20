package code.practice.huawei;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/3/5 下午9:14
 * @description 找最小数
 */
public class _13_FindMinFromNumerString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] num = scanner.next().toCharArray();
        int cutCount = scanner.nextInt();
        int len = num.length;
        StringBuilder minNum = new StringBuilder();
        char minUnit = '9';
        int index = cutCount;
        for (int i = cutCount; i >= 0; i--) {
            if (minUnit >= num[i]) {
                minUnit = num[i];
                index = i;
            }
        }
        minNum.append(minUnit);
        cutCount++;
        while (cutCount < len) {
            minUnit = '9';
            int minIndex = len;
            for (int i = cutCount; i > index; i--) {
                if (minUnit >= num[i]) {
                    minUnit = num[i];
                    minIndex = i;
                }
            }
            minNum.append(minUnit);
            cutCount++;
            index = minIndex;
        }
        System.out.println(minNum.toString());
    }
}
