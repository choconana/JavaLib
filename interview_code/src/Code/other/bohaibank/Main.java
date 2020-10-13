package Code.other.bohaibank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(pass(x, m, n));
    }

    private static int pass(int x, int m, int n) {
        int a = 0, b = 0;
        while (a != x) {
            ++b;
            if (a - n < x) {
                a += m;
            } else if (a - n > x) {
                a -= n;
            } else {
                return b;
            }
        }
        return b;
    }
}
/*
5 77 157
73
 */