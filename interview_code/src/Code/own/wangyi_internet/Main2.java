package Code.own.wangyi_internet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/12 14:43
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println();
        System.out.println(longestString(str));
    }

    private static int longestString(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('b', 0);
        map.put('c', 0);
        map.put('x', 0);
        map.put('y', 0);
        map.put('z', 0);

        int len = str.length();
        int index = 0;
        int preIndex = 0;
        int prefix = 0;
        int max = 0;

        boolean a = true;
        boolean b = true;
        boolean c = true;
        boolean x = true;
        boolean y = true;
        boolean z = true;
        int k = 0;
        boolean flag = true;
        Set<Character> set = new HashSet<>();
        int i  = index;
        while (i < len) {
            i = index;
            for (int j = i; j < len; j++) {
                if (j == index) {
                    preIndex = j;
                }
                char ch = str.charAt(j);

                if (j == index && ch == 'a' || ch == 'b' || ch == 'c' || ch == 'x' || ch == 'y' || ch == 'z') {
                    set.add(ch);
                }
                if (map.containsKey(ch) && k == 0) {
                    k++;
                    prefix = j - index;
                    index = j;

                }
                if (ch == 'a') a = !a;
                if (ch == 'b') b = !b;
                if (ch == 'c') c = !c;
                if (ch == 'c') x = !x;
                if (ch == 'c') y = !y;
                if (ch == 'c') z = !z;

                if (j != preIndex && set.contains(ch)) {
                    // 统计
                    int n = j - preIndex + 1 + prefix;
                    if (a && b && c && x && y && z) {

                        if (flag) {
                            max = max + n;
                        } else {
                            max = Math.max(max, n);
                        }

                    } else {
                        flag = false;
                    }
                    a = true;
                    b = true;
                    c = true;
                    x = true;
                    y = true;
                    z = true;
                    k = 0;
                    set.clear();
                    break;
                }
            }
        }


        return max;
    }
}
