package threadcoreknowledge.synchronizedPractise.shortcoming;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 展示Lock的方法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/11 0:10
 */
public class LockExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        lock.tryLock();
//        lock.tryLock(10, TimeUnit.SECONDS);
    }
}
