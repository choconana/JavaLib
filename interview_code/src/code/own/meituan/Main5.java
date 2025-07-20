package code.own.meituan;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/29 18:05
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        for(int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        for(int j = 0; j < n; j++) {
            B[j] = -1;
        }
        int op = in.nextInt();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < op; i++) {
            int op1 = in.nextInt();
            if(op1 == 1) {
                int k = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                for(int m = 0; m < k; m++) {
                    B[y-1+m] = A[x-1+m];
                }
            }
            else {
                int index = in.nextInt();
                res.add(B[index-1]);
            }
        }
        for(int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
