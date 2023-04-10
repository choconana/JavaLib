package threadcoreknowledge.asyncinvoke;

import java.util.concurrent.CountDownLatch;

/**
 * @author hezhidong
 * @date 2021/7/2 下午2:36
 * @description 用CountDownLatch实现线程异步回调
 */
public class MainThread {
    protected static CountDownLatch countDownLatch;

    public static void asyncMethod() {
        countDownLatch = new CountDownLatch(1);
        System.out.println("这是主线程："+Thread.currentThread());
        new Thread(() -> {
            ChildThread childThread = new ChildThread();
            childThread.asyncInvoke(countDownLatch);
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕");
    }

    public static void main(String[] args) {
        asyncMethod();
    }
}
