package code.own.duxiaoman;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 20:47
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(helper(n, m));
    }

    private static int helper(int n, int m) {
        int sum = 3 * n * (1 + 3  * n) * m / 2;
        return sum;
    }
}
