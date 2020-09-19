package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 演示打印main0，thread-0，thread-1
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 16:45
 */
public class CurrentThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
}
