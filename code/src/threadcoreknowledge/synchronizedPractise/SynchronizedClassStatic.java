package threadcoreknowledge.synchronizedPractise;

/**
 * @Description: 类锁的第一种形式，static方法形式
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 18:04
 */
public class SynchronizedClassStatic implements Runnable {

    static SynchronizedClassStatic instance1 = new SynchronizedClassStatic();
    static SynchronizedClassStatic instance2 = new SynchronizedClassStatic();

    @Override
    public void run() {
        try {
            syncMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void syncMethod() throws InterruptedException {
        System.out.println("我是对象锁的方法修饰符形式，我叫" +
                Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束。");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("finished");
    }
}
