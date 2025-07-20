package code.practice.huawei.niucoder.beginner;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/23 下午8:15
 * @description 取近似值
 */
public class HJ7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String doubleString = in.nextLine();
        double d = Double.valueOf(doubleString);
        if (d < 0.5) {
            System.out.println(0);
            return;
        }
        int actualNum = (int) d;
        int cmpNum = (int) (d - 0.5);
        System.out.println(cmpNum < actualNum ? actualNum : ++actualNum);
    }
}
