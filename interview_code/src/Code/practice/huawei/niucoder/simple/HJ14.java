package Code.practice.huawei.niucoder.simple;

import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/25 下午4:09
 * @description 字符串排序
 */
public class HJ14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.next();
        }
        quickSort(words, 0, words.length - 1);
        print(words);
    }

    public static void quickSort(String[] words, int low, int high) {
        if (low < high) {
            int pivotPos = partition(words, low, high);
            quickSort(words, low, pivotPos - 1);
            quickSort(words, pivotPos + 1, high);
        }
    }

    public static int partition(String[] words, int low, int high) {
        String pivot = words[low];
        while (low < high) {
            while (low < high && strCompare(words[high], pivot) >= 0) high--;
            words[low] = words[high];
            while (low < high && strCompare(pivot, words[low]) >= 0) low++;
            words[high] = words[low];
        }
        words[low] = pivot;
        return low;
    }

    public static int strCompare(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len1 = s1.length();
        int len2 = s2.length();
        int pos;
        for (pos = 0; pos < len1 && pos < len2; pos++) {
            if (c1[pos] != c2[pos]) {
                return c1[pos] - c2[pos];
            }
        }
        return len1 - len2;
    }

    public static void print(String[] words) {
        int len = words.length;
        for (int i = 0; i < len; i++) {
            System.out.println(words[i]);
        }
    }
}
