package code.other.huawei1;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        for (String str : strings) {
            nums.add(Integer.parseInt(str));
        }
        helper(nums);
    }

    public static void helper(ArrayList<Integer> list) {
        ArrayList<String> strings = new ArrayList<>();
        // 1.先转为二进制字符串
        for (int n : list) {
            strings.add(integerToBinaryString(n));
            System.out.println(integerToBinaryString(n));
        }
        // 2.先每行取二进制最低2位，再右移2位
        ArrayList<char[]> chars = new ArrayList<>();
        for (String str : strings) {
            char[] c = new char[2];
            c[0] = str.charAt(str.length() - 2);
            c[1] = str.charAt(str.length() - 1);
            chars.add(c);
        }
        // 右移2位
        ArrayList<String> strings1 = new ArrayList<>();
        for (int n : list) {
            int n1 = n >>> 2;
            System.out.println(n1);
            strings1.add(integerToBinaryString(n1));
        }
        // 3.将当前行取出的最低2位加入下一行的最高2位
        char[] c1 = new char[32];

        // 4.将二进制数组转为十进制
        ArrayList<Long> decimals = new ArrayList<>();

        c1 = strings1.get(0).toCharArray();
        c1[0] = chars.get(strings1.size() - 1)[0];
        c1[1] = chars.get(strings1.size() - 1)[1];
        String str = String.valueOf(c1);
        decimals.add(BinToTen1(str));
        System.out.println(str);
        for (int i = 1; i < strings1.size(); i++) {
            c1 = strings1.get(i).toCharArray();
            c1[0] = chars.get(i - 1)[0];
            c1[1] = chars.get(i - 1)[1];
            String str1 = String.valueOf(c1);
            decimals.add(BinToTen1(str1));
            System.out.println(str1);
        }

        /*for (int i = 0; i < decimals.size(); i++) {
            System.out.print(decimals.get(i));
            if (i < decimals.size() - 1) {
                System.out.printf(" ");
            }
        }*/

        for (int i = 1; i < decimals.size(); i++) {
            System.out.printf(decimals.get(i) + " ");


        }
        System.out.println(decimals.get(0));

    }

    public static String integerToBinaryString(int input) {
        char[] charr = new char[32];
        int k = 1;
        boolean isTrue;
        for (int i = 32 - 1; i >= 0; i--) {
            isTrue = (k & input) == k;
            charr[i] = isTrue ? '1' : '0';
            k = k << 1;
        }
        return new String(charr);
    }

    public static long BinToTen1(String binary) {
        //转化成的十进制
        long ten = 0;
        //整数部分
        int integer = 0;
        for(int i = 0; i < binary.length(); i++) {
            //48为字符'0'对应的ASCII值；
            //ten += (binary.charAt(i) - 48) * Math.pow(2, -(i-(binary.length() - 1)));
            //或者可以将字符转化为字符串，再由字符串转化为数字
            ten += Integer.parseInt(String.valueOf(binary.charAt(i))) * Math.pow(2, -(i-(binary.length() - 1)));
        }
        return ten;

    }

}
// 1.先转为二进制字符串
// 2.先每行取二进制最低2位，再右移2位
// 3.将当前行取出的最低2位加入下一行的最高2位
// 4.逐个转为十进制