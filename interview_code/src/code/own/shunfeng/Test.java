package code.own.shunfeng;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 19:16
 */
public class Test {
    public static void main(String[] args) {
        long i = Integer.MAX_VALUE + 10;
        while (i > 0) {
            i--;
            int n = (int) (Math.random() * 10);
            if (n < 5) {
                System.out.print('X');
            } else {
                System.out.println('Y');
            }
        }
    }
}
