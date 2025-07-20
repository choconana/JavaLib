package jvmspace.bytecode.finallytest;

/**
 * @author hezhidong
 * @date 2023/5/13 下午1:17
 * @description
 */
public class FinallyWithSystemExitTest {
    public static void main(String[] args) {
        try {
            System.out.println("try to do sth.");
            throw new RuntimeException("oops! there is sth. wrong.");
        } catch (Exception e) {
            System.out.println("catch exception");

            System.exit(1);
        } finally {
            System.out.println("finally");
        }
    }
}
