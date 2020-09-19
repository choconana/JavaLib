package juc.immutable;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 23:52
 */
public class FinalStringDemo2 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getWukong();
        String c = b + 2;
        System.out.println(a == c);
    }


    private static String getWukong() {
        return "wukong";
    }
}
