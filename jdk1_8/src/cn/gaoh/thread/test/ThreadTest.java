package cn.gaoh.thread.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: gaoh
 * @Date: 2021/1/30 16:41
 * @Version: 1.0
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("running...");
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t1.getState());
            LockSupport.unpark(t1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        condition();

    }


    public static void condition() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                condition1.await();
                System.out.println("t1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                condition2.await();
                System.out.println("t2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        condition1.signal();
        condition2.signal();
        lock.unlock();


    }

}
