package threadcoreknowledge.synchronizedPractise.principle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 23:16
 */
public class SynchronizedToLock {
    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("我是synchronized形式的锁");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock s = new SynchronizedToLock();
        s.method1();
        s.method2();
    }
}
