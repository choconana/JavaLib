package A360;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/22 20:38
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] num1 = new int[2];
        int[] num2 = new int[10];
        int i = 0;
        num1[0] = scanner.nextInt();
        num1[1] = scanner.nextInt();
        String str = scanner.nextLine();
        num2[0] = scanner.nextInt();
        num2[1] = scanner.nextInt();
        num2[3] = scanner.nextInt();
        hepler(num1, num2);
    }

    public static void hepler(int[] num1, int[] num2) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= num1[0]; i++) {
            list.add(i);
        }
        for (int i = 0; i < num1[1]; i++) {
            if (num2[i] == 1) {
                list = toLast(list);
            } else {
                list = swap(list);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size()) System.out.print(" ");
        }
    }

    public static LinkedList<Integer> toLast(LinkedList<Integer> list) {
        list.addLast(list.getFirst());
        list.removeFirst();
        return list;
    }

    public static LinkedList<Integer> swap(LinkedList<Integer> list) {
        int a = 0;
        int b = 0;
        int tmp = 0;
        for (int i = 2; i < list.size() - 2; i += 2) {
            tmp = list.get(i);
            list.remove(i);
            list.add(i, list.get(i - 1));
            list.remove(i - 1);
            list.add(i - 1, tmp);


        }
        return list;
    }

}
