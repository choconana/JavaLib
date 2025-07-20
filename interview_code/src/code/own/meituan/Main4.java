package code.own.meituan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/29 18:04
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(" ");
        int m = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);
        str = scanner.nextLine();
        strings = str.split(" ");
        int[] nums = new int[n];
        int count = 0;
        for (String s : strings) {
            nums[count++] = Integer.parseInt(s);
        }
        helper(m, n, nums);
    }

    private static void helper(int m, int n, int[] nums) {
        ArrayList<Integer> xs = new ArrayList<>();
        int count = 0;
        int x = 0;
        int k = -1;
        boolean flag = true;
        for (int l = 1; l <= m; l++) {
            for (int r = 1; r <= m; r++) {
                if (l <= r) {
                    for (int i = 0; i < n; i++) {
                        x = nums[i];
                        if ((x > 0 && x < l) || (x > r && x < m + 1)) {
                            xs.add(x);
                            k++;
                            if (xs.size() > 1 && xs.get(k - 1) > xs.get(k) ) {
                                flag = false;
                                break;
                            }
                        }
                        flag = true;
                    }
                    k = -1;
                    if (flag) {
                        count++;
                    }
                }
                xs.clear();
            }
        }
        System.out.println(count);
    }

    public static boolean increment(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }
}
