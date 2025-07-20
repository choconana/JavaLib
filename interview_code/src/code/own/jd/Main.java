package code.own.jd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        helper(n);
    }

    public static void helper(int n) {
//        char[] chars = new char[3];
//        chars[0] = '2';
//        chars[1] = '3';
//        chars[2] = '5';
        int[] a = new int[3];
//        int[] b = new int[3];
//        int[] c = new int[3];
//        int[] d = new int[3];
//
        a[0] = 2;
        a[1] = 3;
        a[2] = 5;
//
//        b[0] = 20;
//        b[1] = 30;
//        b[2] = 50;
//
//        c[0] = 200;
//        c[1] = 300;
//        c[2] = 500;
//
//        d[0] = 2000;
//        d[1] = 3000;
//        d[2] = 5000;
//        for (int i = 0; i < 3; i++) {
//
//        }
//        int param = (int) (Math.log(n) / Math.log(3));
//        int diff = (int) (n - Math.pow(3, param));
        String s = new String();
        s.split("[");
        int[] nums = new int[7];
        for (int i = 0; i < 6; i++) {
            nums[i] = (int) Math.pow(3, i + 1);
            if (i > 0) {
                nums[i] += nums[i - 1];
            }
//            System.out.println(nums[i]);
        }
        int record = 0;
        int k = 0;
        int result = 0;
        while (n > 0) {
            for (int i = 0; i < 6; i++) {
                if (nums[i] < n && n <= nums[i + 1]) {
                    record = i;
                }
            }
            k = n / nums[record];
            n = n % nums[record];
            result += a[k - 1] * (int)Math.pow(10, (record));
            System.out.println(result);

        }

//        int m = n % nums[record];
//        System.out.println(k + ", " + m);
//        result = k * (record + 1);
//        System.out.println(record + ", " + (n - nums[record]));
//        System.out.println(param + ", " + diff);
    }
}
