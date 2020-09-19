package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。
 * start先执行不代表线程先启动
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/12 17:37
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "is going to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new WaitNotifyAll());
        Thread thread2 = new Thread(new WaitNotifyAll());
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    System.out.println("Thread3 notified");
                }
            }
        });
        thread1.start();
        thread2.start();
//        Thread.sleep(200);
        thread3.start();
    }
}
