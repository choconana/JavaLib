package Code.own.wzbank;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/27 19:45
 */

public class Main {

    public static int[] res(int[] nums, int[] a) {
        Arrays.sort(nums);
        int len = a.length;
        int l1 = nums.length;
        int[] result = new int[len];
        for(int i = 0; i < len; i++) {
            int k = a[i];
            if(nums[0] >= k) {
                result[i] = nums[0];
                continue;
            }
            if(nums[l1-1] <= k) {
                result[i] = nums[l1-1];
                continue;
            }
            for(int j = 0; j < l1 -1; j++) {
                if(nums[j] <= k && nums[j+1] >= k) {
                    result[i] = Math.abs(nums[j]-k)-Math.abs(nums[j+1]-k)<=0?nums[j]:nums[j+1];
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] nq = s.split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        String qs = in.nextLine();
        int[] number = new int[n];
        String[] nums = qs.split(" ");
        for(int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(nums[i]);
        }
        int[] a = new int[q];
        for(int i=0; i < q; i++) {
            a[i] = Integer.parseInt(in.nextLine());
        }
        int[] result = res(number, a);
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}
/*
5 5
1 2 3 4 5
3
0
7
2
5

 */