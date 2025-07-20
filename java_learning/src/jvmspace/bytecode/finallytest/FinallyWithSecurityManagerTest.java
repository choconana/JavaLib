package jvmspace.bytecode.finallytest;

/**
 * @author hezhidong
 * @date 2023/5/13 下午1:47
 * @description
 */
public class FinallyWithSecurityManagerTest {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                throw new ThreadDeath();
            }
        });
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
