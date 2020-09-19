package threadcoreknowledge.uncaughtexception;

/**
 * @Description: 使用自己写的UncaughtExceptionHandler
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 20:29
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        // 将自己写的UncaughtExceptionHandler设置为默认的UncaughtExceptionHandler
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
        Thread.sleep(300);

    }
}
