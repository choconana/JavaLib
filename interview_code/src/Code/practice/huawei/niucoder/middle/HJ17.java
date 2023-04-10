package Code.practice.huawei.niucoder.middle;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author hezhidong
 * @date 2023/3/4 下午5:30
 * @description 坐标移动
 */
public class HJ17 {

    static Set<Character> directionSet = new HashSet<Character>() {
        {
            add('A');
            add('D');
            add('W');
            add('S');
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] movements = str.split(";");
        int[] point = new int[2];
        for (String s : movements) {
            int num;
            if ((num = filter(s)) > 0) {
                move(point, s.charAt(0), num);
            }
        }
        System.out.println(point[0] + "," + point[1]);
    }

    public static int filter(String str) {
        if (str.length() == 2) {
            if (directionSet.contains(str.charAt(0)) && Character.isDigit(str.charAt(1))) {
                return Integer.valueOf(str.substring(1, 2));
            }
        } else if (str.length() == 3) {
            if (directionSet.contains(str.charAt(0)) && Character.isDigit(str.charAt(1)) && Character.isDigit(str.charAt(2))) {
                return Integer.valueOf(str.substring(1, 3));
            }
        }

        return -1;
    }

    public static void move(int[] point, char direction, int distance) {
        switch (direction) {
            case 'A':
                point[0] -= distance;
                break;
            case 'D':
                point[0] += distance;
                break;
            case 'W':
                point[1] += distance;
                break;
            case 'S':
                point[1] -= distance;
                break;
            default:
                return;
        }
    }
}
