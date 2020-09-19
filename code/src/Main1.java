import java.util.HashMap;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/21 20:14
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        add(num);
    }

    public static void add(int num) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = num / 2;;
        int right = 0;
        if (num % 2 == 1) {
            right = num / 2 + 1;
        } else {
            right = num / 2;
        }
        for (int i = 0; i < num / 2; i++) {
            left--;
            right++;
            if(left < 100 || right > 1000) break;
            if (helper(left, right)) {
                count++;
                map.put(left, right);
            }
        }
        System.out.println(count);
        for (int k : map.keySet()) {
            System.out.println(k + " " + map.get(k));
        }
    }

    public static boolean helper(Integer left, Integer right) {
        char[] c1 = left.toString().toCharArray();
        char[] c2 = right.toString().toCharArray();
        if ((c1[0] != c1[1] && c1[1] != c1[2]) &&
                (c2[1] == c2[2] && c2[1] != c2[0]) &&
                (c1[0] == c2[0] && c1[2] == c2[2])) {
            return true;
        } else {
            return false;
        }
    }
}
