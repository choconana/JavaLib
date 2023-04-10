package threadcoreknowledge.asyncinvoke;

import java.util.concurrent.CountDownLatch;

/**
 * @author hezhidong
 * @date 2021/7/2 下午2:41
 * @description 子线程
 */
public class ChildThread {
    public void asyncInvoke(CountDownLatch countDownLatch) {
        System.out.println(Thread.currentThread());
        countDownLatch.countDown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行完毕");
    }
}
