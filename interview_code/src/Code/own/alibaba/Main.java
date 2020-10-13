package Code.own.alibaba;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String string = scanner.nextLine();
        ArrayList<String[]> stringLists = new ArrayList<>();
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            if (string.equals("")) break;
            String[] strings = string.split(" ");
            stringLists.add(strings);
        }
        helper(stringLists);
    }

    public static void helper(ArrayList<String[]> list) {
        for (String[] strings : list) {
            int num = Integer.valueOf(strings[0]);
            int len = strings.length;
            int len1 = strings[1].length();
            int count = 0;
            int sum = 0;
            ArrayList<Integer> params = new ArrayList<>();
            ArrayList<Integer> params1 = new ArrayList<>();
            if (len1 == 1) {
                count = strings[2].charAt(0) - strings[1].charAt(0) - 1;
                if (count > 0) {
                    System.out.println(count);
                } else {
                    System.out.println(0);
                }
                continue;
            }
            if (strings[1].equals(strings[2])) {
                System.out.println(0);
                continue;
            }
            for (int i = len1 - 1; i >= 0; i--) {
                count = strings[2].charAt(i) - strings[1].charAt(i) - 1;
                if (count < 0) {
                    count = 0;
                    continue;
                } else {
                    if (i <= len1 - 2) {
                        params1.add(strings[1].charAt(i) - 'a' + 1);
                        params.add(strings[2].charAt(i + 1) - 'a');
                    } else {
                        sum = count;
                    }
                }
            }
            count += 1;
            for (int i : params) {
                count *= i;
            }
            int n = 0;
            if (params1.size() > 0) {
                n = params1.get(0);
                for (int i = 1; i < params1.size(); i++) {
                    n *= params1.get(i);
                }
            }
            System.out.println(count - n);
        }
    }
}
/*

4
1 z a
1 a z
2 az bb
3 bbb bbb

*/
