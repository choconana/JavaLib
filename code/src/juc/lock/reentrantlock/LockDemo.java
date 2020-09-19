package juc.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示ReentrantLock的基本用法，演示被打断: 打印字符串
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/14 17:12
 */
public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }

    private void init() {
        final  Outputer outputer = new Outputer();
        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("coco");
            }
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("chocolate");
            }
        }).start();
    }

    static class Outputer {
        Lock lock = new ReentrantLock();

        // 打印字符串
        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }
    }
}
