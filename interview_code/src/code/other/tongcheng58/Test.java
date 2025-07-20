package code.other.tongcheng58;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 20:49
 */
public class Test {
    public static void main(String[] args) {
        check(null);
    }
    public static void check(Object arg) {
        System.out.println("1");
    }
    public static void check(String arg) {
        System.out.println("2");
    }
}
