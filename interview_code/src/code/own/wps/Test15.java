package code.own.wps;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/29 19:19
 */
public class Test15 {
    static int count = 0;

    public static void main(String[] args) {
        f(10);
        System.out.println(count);
    }

    public static int f(int x) {
        count++;
        if (x <= 2) {
            return 1;
        }
        return f(x - 2) + f(x - 4) + 1;
    }
}
