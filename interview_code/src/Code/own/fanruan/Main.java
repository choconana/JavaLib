package Code.own.fanruan;

import javafx.util.Pair;

import java.util.*;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/20 10:33
 */
public class Main {
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        ArrayList<LinkedList<Pair<Integer, Integer>>> arr = new ArrayList<>();
        for (int i = 1; i <= nums[0]; i++) {
            String[] strs = scanner.nextLine().split(" ");
            LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
            for (int j = 0; j < nums[i]; j++) {
                String[] strs1 = strs[j].split(":");
                Pair<Integer, Integer> map = new Pair<>(Integer.valueOf(strs1[0]), Integer.valueOf(strs1[1]));
                list.add(map);
            }
            arr.add(list);
        }
        merge(arr, 0, arr.size() - 1);
        return;
    }

    private static LinkedList<Pair<Integer, Integer>> merge(ArrayList<LinkedList<Pair<Integer, Integer>>> arr, int left, int right) {
        if (left == right) {
            return arr.get(left);
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        return mergeTwo(merge(arr, left, mid), merge(arr, mid + 1, right));
    }

    private static LinkedList<Pair<Integer, Integer>> mergeTwo(LinkedList<Pair<Integer, Integer>> a, LinkedList<Pair<Integer, Integer>> b) {
        if (a.isEmpty() || b.isEmpty()) {
            return a.isEmpty() ? a : b;
        }
        LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            Pair<Integer, Integer> pair1 = new Pair<>(a.get(i).getKey(), a.get(i).getValue());
            Pair<Integer, Integer> pair2 = new Pair<>(b.get(j).getKey(), b.get(j).getValue());
            Integer key1 = pair1.getKey();
            Integer key2 = pair2.getKey();
            Integer v1 = pair1.getValue();
            Integer v2 = pair2.getValue();
            if (key1 < key2) {
                if (map.containsKey(key1)) {
//                    pair1 = new Pair<>(key1, )
                    map.put(key1, map.get(key1) + v1);
//                    list.set();
                } else {
                    map.put(key1, v1);
                    list.add(a.get(i));
                }
                i++;
            } else {
                list.add(b.get(j));
                if (map.containsKey(key2)) {
                    map.put(key2, map.get(key2) + v2);
                } else {
                    map.put(key2, v2);
                }
                j++;
            }
        }
        int index = 0;
        LinkedList<Pair<Integer, Integer>> list1 = new LinkedList<>();
        if (i == a.size() - 1) {
            index = j;
            list1.addAll(b.subList(j + 1, b.size() - 1));
        } else {
            index = i;
            list1.addAll(a.subList(i, a.size() - 1));
        }
        list.addAll(index, list1);
        return list;
    }
}
/*
3 3 4 3
1:5 3:8 5:1
1:2 2:2 4:3 5:2
2:3 4:4 6:3
 */