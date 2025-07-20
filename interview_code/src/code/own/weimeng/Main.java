package code.own.weimeng;

import java.util.HashSet;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/10/11 16:15
 */
public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"a","a","b","b","c"};
        String[] res = countString(strings);
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static String[] countString(String[] inputArray) {
        HashSet<String> res = new HashSet<>();
        HashSet<String> set = new HashSet<>();
        for (String s : inputArray) {
            if (set.contains(s)) {
                res.add(s);
            } else {
                set.add(s);
            }
        }
        String[] ans = new String[res.size()];
        int count = 0;
        for (String s : res) {
            ans[count++] = s;
        }
        return ans;
    }
}
