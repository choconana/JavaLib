package juc.immutable;

/**
 * @Description: 真假美猴王
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 23:35
 */
public class FinalStringDemo1 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String c = "wukong";
        String d = b + 2;
        String e = d + 2;
        System.out.println((a == d));
        System.out.println((a == e));
    }
}
