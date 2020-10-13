package Code.own.huawei;

import java.util.Scanner;

public class Main2 {

    private static int[][] a = {{-1,0},{1,0},{0,-1},{0,1}};
    private static int rows;
    private static int cols;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(longestPath(matrix));
    }

    private static int longestPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        cols = matrix[0].length;
        int[][] record = new int[rows][cols];
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res = Math.max(res, dfs(matrix, i, j, record));
            }
        }
        return res;
    }

    private static int dfs(int[][] matrix, int row, int col, int[][] record) {
        if (record[row][col] != 0) {
            return record[row][col];
        }
        ++record[row][col];
        for (int[] b : a) {
            int row1 = row + b[0];
            int col1 = col + b[1];
            if (row1 >= 0 && row1 < rows && col1 >= 0 && col1 < cols && matrix[row1][col1] > matrix[row][col]) {
                record[row][col] = Math.max(record[row][col], dfs(matrix, row1, col1, record) + 1);
            }
        }
        return record[row][col];
    }
}
