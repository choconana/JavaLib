package juc.threadpool;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author hezhidong
 * @date 2022/4/25 上午10:56
 * @description
 */
public class ParallelTest {
    public static void main(String[] args) throws Exception {
        Runnable r1 = () -> {
            try {
                int b = System.in.read();
                System.out.println(Thread.currentThread().getName() + " your input is: " + b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Runnable r2 = () -> {
            System.out.println(Thread.currentThread().getName() + " hello world");
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.submit(r1).get();
        System.out.println("middle ");
        scheduledExecutorService.submit(r2).get();

        System.out.println("finish");
        scheduledExecutorService.shutdown();
    }
}
