package juc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/17 22:27
 */
public class ShutDownThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; ++i) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        executorService.shutdown();
//        System.out.println(executorService.awaitTermination(7L, TimeUnit.SECONDS));
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(15000);
//        System.out.println(executorService.isTerminated());

//        List<Runnable> runnableList = executorService.shutdownNow();
        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
