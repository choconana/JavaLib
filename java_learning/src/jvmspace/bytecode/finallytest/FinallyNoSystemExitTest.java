package jvmspace.bytecode.finallytest;

/**
 * @author hezhidong
 * @date 2023/5/13 下午1:24
 * @description
 */
public class FinallyNoSystemExitTest {
    public static void main(String[] args) {
        try {
            System.out.println("try to do sth.");
            throw new RuntimeException("oops! there is sth. wrong.");
        } catch (Exception e) {
            System.out.println("yahaha! catch the exception");
        } finally {
            System.out.println("execute finally");
        }
    }
}
