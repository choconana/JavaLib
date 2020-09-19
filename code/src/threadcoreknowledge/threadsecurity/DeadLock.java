package threadcoreknowledge.threadsecurity;

/**
 * @Description: 第二种线程安全问题，演示死锁
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 22:49
 */
public class DeadLock implements Runnable {

    int flag = 1;

    static Object object1 = new Object();
    static Object object2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread2拿到object2锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (object2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock instance1 = new DeadLock();
        DeadLock instance2 = new DeadLock();
        instance1.flag = 1;
        instance2.flag = 0;
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
    }
}
