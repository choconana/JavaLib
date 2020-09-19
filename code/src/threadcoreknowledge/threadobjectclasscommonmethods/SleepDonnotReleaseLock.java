package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示sleep不释放lock（lock本身需要手动释放）
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 10:45
 */
public class SleepDonnotReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取到锁");
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        SleepDonnotReleaseLock sleepDonnotReleaseLock = new SleepDonnotReleaseLock();
        new Thread(sleepDonnotReleaseLock).start();
        new Thread(sleepDonnotReleaseLock).start();
    }
}
