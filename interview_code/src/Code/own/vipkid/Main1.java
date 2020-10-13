package Code.own.vipkid;

import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        String str = new String();
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (str.equals("")) break;
            list.add(Integer.parseInt(str));
        }
        helper(list);
    }

    public static void helper(ArrayList<Integer> list) {
        for (int n : list) {
            int len = 2 * n;
            int[][] metrix = new int[len][len];
            int num = 1;
            int row = 0;
            int col = 0;
            for (int i = 0; i < n / 2; i++) {
                for (row = i, col = i; col < n - i - 1; col++) {
                    metrix[row][col] = num;
                    metrix[row][len - col - 1] = num;
                    metrix[len - row - 1][len - col - 1] = num;
                    metrix[len - row - 1][col] = num;
                    num++;
                }
                for (row = i, col = n - i - 1; row < n - i - 1; row++) {
                    metrix[row][col] = num;
                    metrix[row][len - col - 1] = num;
                    metrix[len - row - 1][len - col - 1] = num;
                    metrix[len - row - 1][col] = num;
                    num++;
                }
                for (row = n - i - 1, col = n - i - 1; col > i; col--) {
                    metrix[row][col] = num;
                    metrix[row][len - col - 1] = num;
                    metrix[len - row - 1][len - col - 1] = num;
                    metrix[len - row - 1][col] = num;
                    num++;
                }
                for (row = n - i - 1, col = i; row > i; row--) {
                    metrix[row][col] = num;
                    metrix[row][len - col - 1] = num;
                    metrix[len - row - 1][len - col - 1] = num;
                    metrix[len - row - 1][col] = num;
                    num++;
                }
            }
            if (n % 2 == 1) {
                metrix[n / 2][n / 2] = num;
                metrix[n / 2][len - n / 2 - 1] = num;
                metrix[len - n / 2 - 1][len - n / 2 - 1] = num;
                metrix[len - n / 2 - 1][n / 2] = num;
                num++;
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    System.out.print(metrix[i][j]);
                    if (j < len - 1) System.out.printf(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
