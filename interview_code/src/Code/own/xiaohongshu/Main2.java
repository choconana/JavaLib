package Code.own.xiaohongshu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/30 20:25
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        ArrayList<int[]> list = new ArrayList<>();
        while (n > 0) {
            String[] str =  scanner.nextLine().split(" ");
            int[] num = new int[2];
            num[0] = Integer.parseInt(str[0]);
            num[1] = Integer.parseInt(str[1]);
            list.add(num);
            n--;
        }
        helper(list);
    }

    public static void helper(ArrayList<int[]>list) {
        Collections.sort(list, (c1, c2) -> c1[0] - c2[0]);
        int len = list.size();
        int tmp = 0;
        int max = 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int n = list.get(i)[1];
            int m = list.get(i)[0];
            // 若前一个比小，嵌套的个数在此基础上+1，否则往后找到第一个比n小的数，嵌套个数在此基础上+1
            if (list.get(i - 1)[0] < m && list.get(i - 1)[1] < n) {
                dp[i] = dp[i - 1] + 1;
            } else {
                for (int j = i - 2; j >= 0; j--) {
                    if (list.get(j)[0] < m && list.get(j)[1] < n) {
                        dp[i] = dp[j] + 1;
                        break;
                    }
                }
            }
            tmp = Math.max(dp[i], dp[i - 1]);
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }

}
