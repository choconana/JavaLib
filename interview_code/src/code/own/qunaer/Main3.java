package code.own.qunaer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/23 19:57
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
//        char[] rep = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m'};
        String[] strs = scanner.nextLine().split(" ");
        String[] pokers = new String[n];
        for (int i = 0; i < n; i++) {
            if (strs[i].length() == 3) {
                pokers[i] = "j" + strs[i].charAt(0);
            } else {
                char c = strs[i].charAt(1);
                switch (c) {
                    case 'A':
                        pokers[i] = "a" + strs[i].charAt(0);
                        break;
                    case '2':
                        pokers[i] = "b" + strs[i].charAt(0);
                        break;
                    case '3':
                        pokers[i] = "c" + strs[i].charAt(0);
                        break;
                    case '4':
                        pokers[i] = "d" + strs[i].charAt(0);
                        break;
                    case '5':
                        pokers[i] = "e" + strs[i].charAt(0);
                        break;
                    case '6':
                        pokers[i] = "f" + strs[i].charAt(0);
                        break;
                    case '7':
                        pokers[i] = "g" + strs[i].charAt(0);
                        break;
                    case '8':
                        pokers[i] = "h" + strs[i].charAt(0);
                        break;
                    case '9':
                        pokers[i] = "i" + strs[i].charAt(0);
                        break;
                    case 'J':
                        pokers[i] = "k" + strs[i].charAt(0);
                        break;
                    case 'Q':
                        pokers[i] = "l" + strs[i].charAt(0);
                        break;
                    case 'K':
                        pokers[i] = "m" + strs[i].charAt(0);
                        break;
                }
            }
        }
        System.out.println(pokersType(n, pokers));
    }

    private static String pokersType(int n, String[] pokers) {
        Arrays.sort(pokers);
        int count = 0;
        int max = 0;
        int duizi = 0;
        for (int i = n - 1; i > 0; i--) {
            count = 0;
            while ( i > 1) {
                if (pokers[i].charAt(0) == pokers[i - 1].charAt(0)) {
                    count++;
                }
                i--;
            }
            if (count == 2) {
                duizi++;
            }
            max = Math.max(max, count);
        }
        boolean flag = true;
        for (int i = 0; i < n - 1; i++) {
            if (pokers[i].charAt(1) != pokers[i + 1].charAt(1)) {
                flag = false;
            }
        }
        for (int i = n - 1; i > 0; i++) {
            while (i > 1) {
                if (pokers[i].charAt(0) - 1 == pokers[i - 1].charAt(0)) {
                    count++;
                } else {
                    count = 0;
                }
                i--;
            }

        }
        if (count == 5 && flag) {
            return "TongHuaShun";
        } else if (max == 4) {
            return "SiTiao";
        } else if (flag) {
            return "TongHua";
        } else if (count == 5) {
            return "ShunZi";
        } else if (max == 3) {
            return "SanTiao";
        } else if (max == 2 && duizi == 2) {
            return "LiangDui";
        } else if (max == 2 && duizi == 1) {
            return "YiDui";
        } else {
            return "GaoPai";
        }
    }
}
