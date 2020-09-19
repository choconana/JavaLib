package aqs;

import java.util.Collections;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description: 用AQS实现一个简单的线程协作器
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/3 17:28
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }


    private class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
            setState(0);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            // getState为1时，表示门闩可以打开，返回正数，不必进入等待队列，可以获取到锁
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");

                    oneShotLatch.await();
                    System.out.println("开闸放行" + Thread.currentThread().getName() + "，继续运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();

        // 看后面的线程是否能被拦住
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");

                    oneShotLatch.await();
                    System.out.println("开闸放行" + Thread.currentThread().getName() + "，继续运行");
                }
            }).start();
        }
    }

}
