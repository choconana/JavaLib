package code.practice.huawei.niucoder.simple;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author hezhidong
 * @date 2023/2/24 下午11:13
 * @description 合并表记录
 */
public class HJ8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Integer index = scanner.nextInt();
            int value = scanner.nextInt();
            if (!map.containsKey(index)) {
                map.put(index, value);
            } else {
                map.put(index, Integer.valueOf(map.get(index)) + value);
            }
        }
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
