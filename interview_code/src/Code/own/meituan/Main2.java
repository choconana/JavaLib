package Code.own.meituan;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static int[] res(int[][] nums) {
        int m = nums.length;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(nums[0][0]);
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(!res.contains(nums[i][j])) {
                    res.add(nums[i][j]);
                    break;
                }

            }
        }
        int[] res_1 = new int[m];
        for(int i = 0; i <res.size(); i++) {
            res_1[i] = res.get(i);
        }
        return res_1;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int[][] eg = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                eg[i][j] = in.nextInt();
            }
        }
        int[] nums = res(eg);
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
    }
}
