package Code.own.wzbank;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/27 19:45
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings1 = scanner.nextLine().split(" ");
        int[] execute = new int[3];
        for (int i = 0; i < 3; i++) {
            execute[i] = Integer.parseInt(strings1[i]);
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();
        int[][] query = new int[q][2];
        for (int i = 0; i < q; i++) {
            query[i][0] = scanner.nextInt();
            query[i][1] = scanner.nextInt();
        }
        int[] square = new int[2];
        square[0] = n;
        square[1] = m;
        executeSquare(execute, square, query);
        for (int i = 0; i < query.length; i++) {
            System.out.println(query[i][0] + " " + query[i][1]);
        }
    }

    private static void executeSquare(int[] execute, int[] square, int[][] query) {
        for (int j = 0; j < query.length; j++) {
            int[] tmp = square;
            for (int i = 0; i < execute[0]; i++) {
                rotate(tmp[0], tmp[1], query[j]);
                swap(tmp);
            }
            for (int i = 0; i < execute[1]; i++) {
                flip(tmp[0], tmp[1], query[j]);
            }
            for (int i = 0; i < execute[2]; i++) {
                contrarotate(tmp[0], tmp[1], query[j]);
                swap(tmp);
            }
        }
    }

    private static void swap(int[] sqaure) {
        int tmp = sqaure[0];
        sqaure[0] = sqaure[1];
        sqaure[1] = tmp;
    }

    private static void rotate(int n, int m, int[] query) {
            int row = query[0];
            int col = query[1];
            query[0] = col;
            query[1] = n - row + 1;
    }

    private static void flip(int n, int m, int[] query) {
            int col = query[1];
            query[1] = m - col + 1;
    }

    private static void contrarotate(int n, int m, int[] query) {
            int row = query[0];
            int col = query[1];
            query[1] = row;
            query[0] = m - col + 1;
    }
}
/*
1 1 1
4 5
3
1 1
3 4
2 5
 */