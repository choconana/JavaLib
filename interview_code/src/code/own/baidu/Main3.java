package code.own.baidu;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int res = (int) (upstairs(n, m) % (Math.pow(10, 9) + 7));
        System.out.println(res);
    }

    private static int upstairs(int n, int m) {
        int sumStep = 0;
        if (n == 0) return 1;
        if (n >= m) {
            for (int i = 1; i <= m; i++) {
                sumStep += upstairs(n - i, m);
            }
        } else {
            sumStep = upstairs(n, n);
        }
        return sumStep;
    }
}
