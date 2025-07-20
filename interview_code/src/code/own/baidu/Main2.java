package code.own.baidu;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] strings = scanner.nextLine().split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : strings) {
            arrayList.add(s);
        }
        helper(arrayList);
    }

    private static void helper(ArrayList<String> strings) {
        Collections.sort(strings, Comparator.reverseOrder());
        long num = 0;
        int record = 0;
        boolean flag = false;
        int len = strings.size() - 1;
        int result = 0;
        for (int i = 0; i <= len; i++) {
            if (strings.get(i).equals("0")) {
                record = i;
                break;
            }
        }
        for (int i = record; i <= len; i++) {
            for (int j = record; j > 0; j--) {
                if (j != record) {
                    swap(strings, j, j - 1);
                }
                StringBuilder sb = new StringBuilder();
                for (String s : strings) {
                    sb.append(s);
                }
                num = Long.parseLong(sb.toString());
                if (num % 90 == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(num);
        }
    }


    public static void swap(ArrayList<String> strings, int i, int j) {
        String tmp = strings.get(i);
        strings.remove(i);
        strings.add(i, strings.get(j));
        strings.remove(j);
        strings.add(j, tmp);
    }
}
