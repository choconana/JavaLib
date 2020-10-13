package Code.own.tiger;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/20 17:10
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long w = scanner.nextLong();
        int k = scanner.nextInt();
        scanner.nextLine();
        String[] strings1 = scanner.nextLine().split(" ");
        String[] strings2 = scanner.nextLine().split(" ");
        int[][] costs = new int[n][2];
        for (int i = 0; i < n; i++) {
            costs[i][0] = Integer.parseInt(strings1[i]);
            costs[i][1] = Integer.parseInt(strings2[i]);
        }
        System.out.println(maxCapital(costs, w, k));
    }

    private static long maxCapital(int[][] costs, long w, int k) {
        Arrays.sort(costs, (c1, c2) -> c1[0] - c2[0]);
        int max = 0;
        int index = 0;
        while (k > 0) {
            max= 0;
            for (int i = 0; i < costs.length; i++) {
                if (costs[i][0] != Integer.MAX_VALUE && costs[i][0] > w) break;
                if (max <= costs[i][1]) {
                    max = costs[i][1];
                    index = i;
                }
            }
            w += max;
            costs[index][0] = Integer.MAX_VALUE;
            costs[index][1] = Integer.MIN_VALUE;
            k--;
        }
        return w;
    }
}
/*
5 3 3
5 4 1 2 8
3 5 3 2 4

15
 */