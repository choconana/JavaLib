package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: volatile适用的情况1
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 20:33
 */
public class UseVolatile1  implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile1 useVolatile1 = new UseVolatile1();
        Thread thread1 = new Thread(useVolatile1);
        Thread thread2 = new Thread(useVolatile1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(useVolatile1.done);
        System.out.println(useVolatile1.realA.get());
    }
}
