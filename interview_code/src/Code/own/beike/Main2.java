package Code.own.beike;

import java.util.HashSet;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine();

        int left = 1, right = len;
        int substr;
        int min = len;
        while (left <= right) {
            substr = left + (right - left) / 2;
            if (hasSameSubstr(substr, len, str) != -1) {
                left = substr + 1;
                if (len >= 2 * (left - 1)) {
                    min = Math.min(min, len - left + 2);
                }
            } else {
                right = substr - 1;
            }
        }
        System.out.println(min);
    }

    public static int hasSameSubstr(int substr, int len, String str) {
        HashSet<String> set = new HashSet<>();
        String tmp;
        for (int i = 0; i < len - substr + 1; i++) {
            tmp = str.substring(i, i + substr);
            if (set.contains(tmp)) return i;
            set.add(tmp);
        }
        return - 1;
    }
}

