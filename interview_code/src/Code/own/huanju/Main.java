package Code.own.huanju;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(nextNarcissisticNumber(153));

    }

    public static long nextNarcissisticNumber (int n) {
        String s = String.valueOf(n);
        String s1 = Integer.valueOf(n).toString();
        System.out.println(s.equals(s1));
        long nar = 0;
        HashMap<Boolean, Long> map = narcissisticNumber(n);
        if (map.containsKey(true)) {
            nar = map.get(true);
            n++;
        }
        for (int i = n; i < Integer.MAX_VALUE; i++) {
            map = narcissisticNumber(i);
            if (map.containsKey(true)) {
                nar = map.get(true);
                break;
            }
        }
        return nar;
    }

    public static HashMap<Boolean, Long> narcissisticNumber (int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int[] nums = new int[len];
        HashMap<Boolean, Long> map = new HashMap<>();
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.valueOf(s.charAt(i) - '0');
        }
        long sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.pow(nums[i], len);
        }
        if (n == sum) {
            flag = true;
        } else {
            flag = false;
        }
        map.put(flag, sum);
        return map;
    }
}
