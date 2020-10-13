package Code.other.abc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/29 10:09
 */
public class Main {
    public static void main(String[] args) {
        String[] strings = {"s1", "s3", "s9", "s4", "h1", "p3", "p2", "q5", "q9", "k2", "k1"};
        strings = pokerSort(strings);
        System.out.printf("[");
        for (String str : strings) {
            System.out.printf(str + ", ");
        }
        System.out.printf("]");
    }

    public static String[] pokerSort(String[] cards) {
        ArrayList<Integer> k = new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<>();
        ArrayList<Integer> h = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        ArrayList<Integer> q = new ArrayList<>();
        for (String str : cards) {
            switch (str.charAt(0)) {
                case 'k':
                    k.add(str.charAt(1) - 48);
                    break;
                case 's':
                    s.add(str.charAt(1) - 48);
                    break;
                case 'h':
                    h.add(str.charAt(1) - 48);
                    break;
                case 'p':
                    p.add(str.charAt(1) - 48);
                    break;
                case 'q':
                    q.add(str.charAt(1) - 48);
                    break;
            }
        }
//        Collections.sort(k, (c1, c2) -> c1 - c2);
//        Collections.sort(s, (c1, c2) -> c1 - c2);
//        Collections.sort(h, (c1, c2) -> c1 - c2);
//        Collections.sort(p, (c1, c2) -> c1 - c2);
//        Collections.sort(q, (c1, c2) -> c1 - c2);
        quickSort(toArray(k), 0, k.size() - 1);
        quickSort(toArray(s), 0, s.size() - 1);
        quickSort(toArray(h), 0, h.size() - 1);
        quickSort(toArray(p), 0, p.size() - 1);
        quickSort(toArray(q), 0, q.size() - 1);
        String[] strings = new String[cards.length];
        int n = 0;
        for (int i = 0; i < k.size(); i++) {
            strings[n++] = "k" + k.get(i);
        }
        for (int i = 0; i < s.size(); i++) {
            strings[n++] = "s" + s.get(i);
        }
        for (int i = 0; i < h.size(); i++) {
            strings[n++] = "h" + h.get(i);
        }
        for (int i = 0; i < p.size(); i++) {
            strings[n++] = "p" + p.get(i);
        }
        for (int i = 0; i < q.size(); i++) {
            strings[n++] = "q" + q.get(i);
        }
        return strings;
    }

    public static int[] toArray(ArrayList<Integer> list) {
        int[] a = new int[13];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public static void quickSort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            while(array[j] >= base && i < j) {
                j--;
            }
            while(array[i] <= base && i < j) {
                i++;
            }

            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        array[left] = array[i];
        array[i] = base;

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
