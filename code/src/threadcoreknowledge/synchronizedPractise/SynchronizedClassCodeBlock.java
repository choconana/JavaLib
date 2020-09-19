package threadcoreknowledge.synchronizedPractise;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 18:15
 */
public class SynchronizedClassCodeBlock implements Runnable {

    static SynchronizedClassCodeBlock instance1 = new SynchronizedClassCodeBlock();
    static SynchronizedClassCodeBlock instance2 = new SynchronizedClassCodeBlock();

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method() throws InterruptedException {
        synchronized (SynchronizedClassCodeBlock.class) {
            System.out.println("我是类锁的第二种形式。我叫" +
                    Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "运行结束。");
        }
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
