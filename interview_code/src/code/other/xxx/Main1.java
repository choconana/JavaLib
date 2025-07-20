package code.other.xxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(",");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : strings) {
            nums.add(Integer.parseInt(s));
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        while (nums.size() > 0) {
            count += helper(nums);
        }
        System.out.println(count);
    }

    public static int helper(ArrayList<Integer> arrayList) {
        int len = arrayList.size();
        int[] product = new int[len];
//        product[0] = arrayList.get(0) * arrayList.get(1);
        int max = arrayList.size() > 1 ? arrayList.get(0) * arrayList.get(1) : arrayList.get(0);
        int cur = 0;
        int record = 0;
        for (int i = 1; i < len; i++) {
            if (i == len - 1) {
                cur = arrayList.get(i - 1) * arrayList.get(i);
            } else {
                cur = arrayList.get(i - 1) * arrayList.get(i) * arrayList.get(i + 1);
                if (max < cur) {
                    max = cur;
                    record = i;
                }
            }
        }
        arrayList.remove(record);
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(record, max);
        return max;
    }
}
