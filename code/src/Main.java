import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/21 18:57
 */
public class Main {

    static int[][] f;
    static int row;
    static int rowTmp;
    static int col;
    static int colTmp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fMatrix(scanner.nextInt());
    }

    public static void fMatrix(int n) {
        f = new int[n][n];
        row = 0;
        rowTmp = n;
        col = 0;
        colTmp = n;

        fibo(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(f[i][j]);
            }
            if (i != n - 1) {
                System.out.println("");
            }
        }
    }

    public static int fibo(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }else {
            int num = fibo(n - 1) + fibo(n - 2);
            f[row][col] = num;
            if (col == colTmp) {
                if (row == rowTmp) {
                    row++;
                } else {

                }
                col++;
            } else {

            }
            return num;
        }
    }
}
