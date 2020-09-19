package threadcoreknowledge.synchronizedPractise;

/**
 * @Description: 对象锁示例2，方发锁形式
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 17:44
 */
public class SynchronizedObjectMethod implements Runnable {

    static SynchronizedObjectMethod instance = new SynchronizedObjectMethod();

    @Override
    public void run() {
        try {
            syncMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void syncMethod() throws InterruptedException {
        System.out.println("我是对象锁的方法修饰符形式，我叫" +
                Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束。");
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
