package Code.own.xiaohongshu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> list = new ArrayList<>();
        String str = new String();
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (str.equals("")) break;
            String[] strings = str.split(" ");
            int[] n = new int[strings.length];
            int count = 0;
            for (String s : strings) {
                n[count++] = Integer.parseInt(s);
            }
            list.add(n);
        }
        helper(list);
    }

    public static void helper(ArrayList<int[]> list) {
        int len = list.size();
        int[][] w = new int[len][len];
        int[] v = new int[len];
        int[][] f= new int[len][len];
    }

    public static void beforeWork(ArrayList<int[]>list, int[][] w, int[] v, int[][] f) {

    }
}
