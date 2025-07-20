package code.own.tongcheng58;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/14 19:30
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] dic = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dic[i][j] = scanner.nextInt();
            }
        }
        System.out.println(district(dic));
    }

    private static int district(int[][] dic) {
        if (dic == null || dic.length == 0) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < dic.length; i++) {
            for (int j = 0; j < dic[0].length; j++) {
                if (dic[i][j] == 1) {
                    num++;
                    dfs(dic, i, j);
                }
            }
        }
        return num;
    }

    private static void dfs(int[][] dic, int i, int j) {
        int r = dic.length;
        int c = dic[0].length;
        if (i < 0 || j < 0 || i >= r || j >= c || dic[i][j] == 0) {
            return;
        }

        dic[i][j] = 0;
        dfs(dic, i - 1, j);
        dfs(dic, i + 1, j);
        dfs(dic, i, j - 1);
        dfs(dic, i, j + 1);
    }
}
