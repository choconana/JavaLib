package threadcoreknowledge.uncaughtexception;

/**
 * @Description:
 * 1. 不加try/catch 抛出4个异常，都带线程名字
 * 2. 加了try/catch，期望捕获到第一个线程的异常，后面的三个线程都不应该运行，期望看到打印出的捕获到的异常
 *    执行时发现，根本没有被捕获的异常，线程234依然运行并且抛出异常
 *
 * 说明线程的异常不能用传统的方法捕获
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 19:53
 */
public class CannotCatchDirectly implements Runnable {
    @Override
    public void run() {
//        try {
            throw new RuntimeException();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//        System.out.println("捕获到异常");
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CannotCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CannotCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CannotCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CannotCatchDirectly(), "MyThread-4").start();
            Thread.sleep(300);
        } catch (RuntimeException e) {
            System.out.println("Caught Exception.");
//            e.printStackTrace();
        }

    }
}
