package code.own.huawei;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] tree = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                tree[i][j] = scanner.nextInt();
            }
        }
        System.out.println(maxSum(tree));
    }

    private static int maxSum(int[][] tree) {
        int res = 0;
        return res;
    }
}
/*
5
1 1 2 3
2 4 -1 -1
3 2 -1 4
4 5 -1 5
5 3 -1 -1

7
*/
