package Code.practice.huawei.niucoder.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/27 下午10:55
 * @description 简单密码
 */
public class HJ21 {

    final static Map<Character, Character> sTable = new HashMap<Character, Character>() {
        {
            put('a', '2');
            put('b', '2');
            put('c', '2');
            put('d', '3');
            put('e', '3');
            put('f', '3');
            put('g', '4');
            put('h', '4');
            put('i', '4');
            put('j', '5');
            put('k', '5');
            put('l', '5');
            put('m', '6');
            put('n', '6');
            put('o', '6');
            put('p', '7');
            put('q', '7');
            put('r', '7');
            put('s', '7');
            put('t', '8');
            put('u', '8');
            put('v', '8');
            put('w', '9');
            put('x', '9');
            put('y', '9');
            put('z', '9');
            put('A', 'b');
            put('B', 'c');
            put('C', 'd');
            put('D', 'e');
            put('E', 'f');
            put('F', 'g');
            put('G', 'h');
            put('H', 'i');
            put('I', 'j');
            put('J', 'k');
            put('K', 'l');
            put('L', 'm');
            put('M', 'n');
            put('N', 'o');
            put('O', 'p');
            put('P', 'q');
            put('Q', 'r');
            put('R', 's');
            put('S', 't');
            put('T', 'u');
            put('U', 'v');
            put('V', 'w');
            put('W', 'x');
            put('X', 'y');
            put('Y', 'z');
            put('Z', 'a');
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawStr = scanner.next();
        char[] rawChar = rawStr.toCharArray();
        StringBuilder encodeStr = new StringBuilder();
        for (char c : rawChar) {
            if (Character.isDigit(c)) {
                encodeStr.append(c);
            } else {
                encodeStr.append(sTable.get(c));
            }
        }
        System.out.println(encodeStr.toString());
    }
}
