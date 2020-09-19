package juc.lock.spinlock;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 自旋锁
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/15 21:38
 */
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        // CAS第一个参数是期望锁的状态，第二个参数更新锁的状态
        // 这里所要表达的是当锁为null，也就是未被获取的状态的时候，由当前线程获取该锁
        while(!sign.compareAndSet(null, current)) {
            System.out.println(Thread.currentThread().getName() + "自旋获取失败，再次尝试");
        }
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        // 这里所要表达的是当锁被当前线程持有时，使当前线程将锁释放
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                    spinLock.unlock();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
