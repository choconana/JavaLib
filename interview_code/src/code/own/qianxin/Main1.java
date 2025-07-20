package code.own.qianxin;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/25 16:15
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = scanner.nextLine().split(",");
        int len = strings.length;
        int[] tasks = new int[len];
        for (int i = 0; i < len; i++) {
            tasks[i] = Integer.parseInt(strings[i]);
        }
        System.out.println(leastTime(tasks, n));
    }

    private static int leastTime(int[] tasks, int n) {
        int[] natures = new int[10];
        for (int i : tasks) {
            natures[i]++;
        }
        Arrays.sort(natures);
        int count = 0;
        while (natures[9] > 0) {
            int i = 0;
            while (i <= n) {
                if (natures[9] == 0) {
                    break;
                }
                if (i < 10 && natures[9 - i] > 0) {
                    natures[9 - i]--;
                }
                count++;
                i++;
            }
            Arrays.sort(natures);
        }
        return count;
    }
}
/*
2
1,1,1,2,3,3,3
 */