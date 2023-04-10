package Code.practice.huawei.niucoder.beginner;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/23 下午9:16
 * @description 截取字符串
 */
public class HJ46 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            int n = scanner.nextInt();
            char[] rowString = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(rowString[i]);
            }
            System.out.println(sb.toString());
        }
    }
}
