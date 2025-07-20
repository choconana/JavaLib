package code.own.vipkid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/28 18:25
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> list = new ArrayList<>();
        String str = new String();
        String[] strings = new String[2];
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (str.equals("")) break;
            strings = str.split(" ");
            int[] num = new int[2];
            num[0] = Integer.parseInt(strings[0]);
            num[1] = Integer.parseInt(strings[1]);
            list.add(num);
        }
        helper(list);
    }

    public static void helper(ArrayList<int[]> list) {
        Collections.sort(list, (c1, c2) -> c1[0] - c2[0]);
        int len = list.size();
//        int[][] result = new int[len][2]
        ArrayList<int[]> result = new ArrayList<>();
        int num = -1;

        for (int i = 0; i < len; i++) {
            if (num < 0 || list.get(i)[0] > result.get(num)[1]) {
                result.add(list.get(i));
                ++num;
            } else {
                result.get(num)[1] = Math.max(list.get(i)[1], result.get(num)[1]);
            }
        }
        for (int[] n : result) {
            System.out.println(n[0] + " " + n[1]);
        }
    }
}
