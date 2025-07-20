package code.own.qunaer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/23 19:57
 */
public class Main {
    static Map<String, Long> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(chooseMember(m, n));
    }

    private static long chooseMember(int m, int n) {
        String key = m+","+n;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return m;
        }
        if (n > m / 2) {
            return chooseMember(m, m - n);
        }
        if (n > 1) {
            if (!map.containsKey(key)) {
                map.put(key, chooseMember(m - 1, n - 1) + chooseMember(m - 1, n));
            }
            return map.get(key);
        }
        return -1;
    }
}
