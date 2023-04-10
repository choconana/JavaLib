package Code.practice.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/21 下午10:23
 * @description 用连续自然数之和来表达整数
 */
public class _1_FindContinuousSequence {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        Integer num = Integer.valueOf(in);
        int[][] numArray = findContinuousSequence(num);
        output(num, numArray);
    }

    public static int[][] findContinuousSequence(int num) {
        int pre = 0, post = 1;
        int len = num / 2 + 1;
        List<int[]> res = new ArrayList<>(len);

        while (post <= len) {
            int sum = (pre + post) * (post - pre + 1) / 2;
            if (sum == num) {
                res.add(convert2Array(pre, post));
                pre++;
                post++;
            } else if (sum < num) {
                post++;
            } else {
                pre++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static int[] convert2Array(int pre, int post) {
        int len = post - pre + 1;
        int[] numArray = new int[len];
        for (int i = 0; i < len; i++) {
            numArray[i] = i + pre;
        }
        return numArray;
    }

    public static void output(int num, int[][] arr) {
        System.out.println(num + "=" + num);
        int alen = arr.length;
        for (int i = alen - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            sb.append(num).append("=");
            int[] sequence = arr[i];
            int slen = sequence.length;
            for (int j = 0; j < slen; j++) {
                sb.append(sequence[j]).append("+");
//                if (j < sequence.length - 1) {
//                    sb.append("+");
//                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
        System.out.println("Result:" + (alen + 1));
    }
}




