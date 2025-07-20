package code.practice.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author hezhidong
 * @date 2023/3/5 上午9:28
 * @description 字符统计及重排
 */
public class _11_CharStatisticAndSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.next().toCharArray();
        String[] statResult = statistic(chars);
        quickSort(statResult, 0, statResult.length - 1);
        print(statResult);
    }

    public static String[] statistic(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.keySet().contains(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        String[] statResult = new String[map.size()];
        int count = 0;
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Character, Integer> e : entrySet) {
            statResult[count++] = e.getKey() + ":" + e.getValue();
        }
        return statResult;
    }

    public static void quickSort(String[] ss, int low, int high) {
        if (low < high) {
            int pivotPos = partition(ss, low, high);
            quickSort(ss, low, pivotPos - 1);
            quickSort(ss, pivotPos + 1, high);
        }
    }

    public static int partition(String[] ss, int low, int high) {
        String pivot = ss[low];
        while (low < high) {
            while (low < high && compareTo(pivot, ss[high]) >= 0) high--;
            ss[low] = ss[high];
            while (low < high && compareTo(ss[low], pivot) >= 0) low++;
            ss[high] = ss[low];
        }
        ss[low] = pivot;
        return low;
    }

    public static int compareTo(String s1, String s2) {
        String[] ss1 = s1.split(":");
        String[] ss2 = s2.split(":");

        if (!ss1[1].equals(ss2[1])) {
            return ss1[1].compareTo(ss2[1]);
        } else {
            if (ss1[0].toLowerCase().equals(ss2[0].toLowerCase())) {
                return ss1[0].compareTo(ss2[0]);
            } else {
                if ((Character.isLowerCase(ss1[0].charAt(0)) && Character.isLowerCase(ss2[0].charAt(0)))
                        || (Character.isUpperCase(ss1[0].charAt(0)) && Character.isUpperCase(ss2[0].charAt(0)))) {
                    return ss2[0].compareTo(ss1[0]);
                } else {
                    return ss1[0].compareTo(ss2[0]);
                }
            }
        }
    }

    public static void print(String[] ss) {
        System.out.println(String.join(";", ss) + ";");
    }
}
