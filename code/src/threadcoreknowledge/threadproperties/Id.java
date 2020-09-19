package threadcoreknowledge.threadproperties;

/**
 * @Description: ID从1开始，JVM运行起来后，我们自己创建的线程的id早已不是2.
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 18:12
 */
public class Id {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID：" + Thread.currentThread().getId());
        System.out.println("子线程的ID：" + thread.getId());
    }
}