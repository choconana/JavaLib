import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author hezhidong
 * @date 2022/8/29 上午9:41
 * @description
 */
public class Main2 {
    public static void main(String[] args) {
//        inc1();
//        inc2();
        System.out.println(testFinally());
        System.out.println(testCatch());
    }

    public static void inc1() {
        int i = 0;
        for (int j = 0; j < 10; j++) {
            i = i++;
        }
        System.out.println(i);
    }

    public static void inc2() {
        int i = 0;
        for (; i < 10; i++) {
        }
        System.out.println(i);
    }

    public static int testFinally() {
        int i = 100;
        try {
            return i;
        } finally {
            i++;
        }
    }

    public static int testCatch() {
        int i = 100;
        try {
            i /= 0;
            return i;
        } catch (NullPointerException e) {
            System.out.println("e");
            i++;
        } catch (ArithmeticException e1) {
            System.out.println("e1");
            i++;
        } catch (Exception e2) {
            System.out.println("e2");
            i++;
        } finally {
            System.out.println("finally");
            i++;
        }
        return i;
    }
}
