package code.practice.huawei;

import java.util.*;

/**
 * @author hezhidong
 * @date 2023/3/5 上午10:34
 * @description 数组去重和排序
 */
public class _12_NumberCutDupAndSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nums = scanner.next().split(",");
        String[] statResult = statistic(nums);
        bubbleSort(statResult);
        print(statResult);
    }

    public static String[] statistic(String[] nums) {
        int len = nums.length;
        List<String> statResult = new ArrayList<>();
        Map<Integer, Integer> memo = new HashMap<>();
        int count = 0;
        for (int i = 0; i < len; i++) {
            int n = Integer.parseInt(nums[i]);
            if (memo.keySet().contains(n)) {
                int index = memo.get(n);
                int amount = Integer.parseInt(statResult.get(index).split(":")[1]) + 1;
                statResult.set(index, n + ":" + amount);
            } else {
                statResult.add(n + ":1");
                memo.put(n, count);
                count++;
            }

        }
        return statResult.toArray(new String[count]);
    }

    public static void bubbleSort(String[] nums) {
        boolean flag;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            flag = false;
            for (int j = len - 1; j > i; j--) {
                if (compareTo(nums[j - 1], nums[j]) < 0) {
                    String tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
                flag = true;
            }
            if (!flag) {
                return;
            }
        }
    }

    public static int compareTo(String s1, String s2) {
        String[] ss1 = s1.split(":");
        String[] ss2 = s2.split(":");
        return ss1[1].compareTo(ss2[1]);
    }

    public static void print(String[] ss) {
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s.split(":")[0]).append(",");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
    }
}
