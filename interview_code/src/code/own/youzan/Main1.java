package code.own.youzan;

import java.util.Arrays;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/10/11 19:39
 */
public class Main1 {
    public int[] fairCandySwap (int[] A, int[] B) {
        // write code here
        Arrays.sort(A);
        Arrays.sort(B);
        int a = 0, b = 0, sum1 = 0, sum2 = 0;
        int[] res = new int[2];
        for (int n : A) {
            sum1 += n;
        }
        for (int n : B) {
            sum2 += n;
        }
        while(true) {
            int count = sum1 - A[a] + B[b] - (sum2 + A[a] - B[b]);
            if (count == 0) {
                res[0] = A[a];
                res[1] = B[b];
                break;
            } else if (count > 0) {
                a++;
            } else {
                b++;
            }
        }
        return res;
    }
}
