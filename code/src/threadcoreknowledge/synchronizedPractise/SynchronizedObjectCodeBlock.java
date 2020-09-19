package threadcoreknowledge.synchronizedPractise;

/**
 * @Description: 对象锁示例1，代码块形式
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 17:11
 */
public class SynchronizedObjectCodeBlock implements Runnable {

    static SynchronizedObjectCodeBlock instance = new SynchronizedObjectCodeBlock();

    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("我是lock1。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock1运行结束。");
        }

        /*synchronized (lock2) {
            System.out.println("我是lock2。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock2运行结束。");
        }*/
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("finished");
    }
}
