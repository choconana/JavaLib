package threadcoreknowledge.synchronizedPractise;

/**
 * @Description: 方法抛出异常后，会释放锁。展示抛出异常前后对比：
 * 一旦抛出了异常，第二个线程会立刻进入同步方法，意味着锁已经释放掉。
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 20:54
 */
public class SynchronizedException implements Runnable {

static SynchronizedException instance = new SynchronizedException();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("我是同步方法1。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
//            throw new Exception();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
//        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public synchronized void method2() {
        System.out.println("我是同步方法2。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
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
