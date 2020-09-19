package threadcoreknowledge.uncaughtexception;

/**
 * @Description: 单线程出现异常：抛出，处理，打印异常堆栈；
 * 多线程出现异常：子线程发生异常，会有什么不同？
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 19:46
 */
public class ExceptionInChildThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; ++i) {
            System.out.println(i);
        }
    }
}
