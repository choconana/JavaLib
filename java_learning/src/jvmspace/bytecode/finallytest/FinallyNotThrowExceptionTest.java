package jvmspace.bytecode.finallytest;

/**
 * @author hezhidong
 * @date 2023/5/13 下午1:36
 * @description
 */
public class FinallyNotThrowExceptionTest {
    public static void main(String[] args) {
        try {
            System.out.println("try to do sth.");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("yahaha! catch the exception");
            System.exit(1);
        } finally {
            System.out.println("execute finally");
        }
    }
}
