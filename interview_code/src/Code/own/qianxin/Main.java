package Code.own.qianxin;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/25 16:15
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(upstairs(n));
    }

    private static int upstairs(int n) {
        int res = 0;
        if (n == 0) {
            res = 0;
        } else if (n == 1) {
            res = 1;
        } else {
            res = 2 * upstairs(n - 1);
        }
        return res;
    }
}
