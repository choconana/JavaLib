package code.own.beike;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> list = new ArrayList<>();
        while (n > 0) {
            String[] strings = scanner.nextLine().split(" ");
            int left = judge(strings[0], strings);
            int right = judge(strings[1], strings);
            if (left > right) {
                list.add("left");
            } else if (left == right) {
                list.add("same");
            } else {
                list.add("right");
            }
            n--;
        }
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static int judge(String str, String[] stirngs) {
        int count = 0;
        for (int i = 2; i <= 3; i++) {
            switch (str) {
                case "S":
                    if (stirngs[i].equals("J")) {
                        count++;
                    } else {
                        count--;
                    }
                    break;
                case "J":
                    if (stirngs[i].equals("B")) {
                        count++;
                    } else {
                        count--;
                    }
                    break;
                case "B":
                    if (stirngs[i].equals("S")) {
                        count++;
                    } else {
                        count--;
                    }
                    break;
            }
        }
        return count;
    }
}
