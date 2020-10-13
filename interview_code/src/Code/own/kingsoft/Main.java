package Code.own.kingsoft;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/16 18:38
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        fbTri(n);
    }

    private static void fbTri(int n) {
        int[][] tri = new int[n + 1][2 * n + 2];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(tri[i], 1);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j <= 2) {
                    tri[i][j] = 1;
                } else if (j <= i) {
                    tri[i][j] = tri[i][j - 1] + tri[i][j - 2];
                } else {
                    tri[i][j] = tri[i][2 * i - j];
                }
                if (tri[i][j] != 0) {
                    System.out.print(tri[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
