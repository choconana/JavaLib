package jmm;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 不适用volatile的场景
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 20:23
 */
public class NoVolatile implements Runnable {

    volatile int a = 0;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            a++;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile noVolatile = new NoVolatile();
        Thread thread1 = new Thread(noVolatile);
        Thread thread2 = new Thread(noVolatile);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(noVolatile.a);
        System.out.println(noVolatile.realA.get());
    }
}
