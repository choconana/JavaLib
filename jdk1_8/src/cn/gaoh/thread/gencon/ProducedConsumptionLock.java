package cn.gaoh.thread.gencon;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: gaoh
 * @Date: 2021/3/7 21:03
 * @Version: 1.0
 */
public class ProducedConsumptionLock<T> implements ProCon<T> {
    //存放数据
    private final LinkedList<T> lists = new LinkedList<>();

    ReentrantLock lock = new ReentrantLock();
    //生产
    Condition produced = lock.newCondition();
    //消费
    Condition consume = lock.newCondition();

    /**
     * 生产者
     *
     * @param val
     */
    @Override
    public void put(T val) {
        lock.lock();
        try {
            int max = 10;
            while (lists.size() == max) {
                try {
                    produced.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lists.add(val);
            consume.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 消费者
     *
     * @return
     */
    @Override
    public T get() {
        lock.lock();
        try {
            while (lists.size() == 0) {
                try {
                    consume.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = lists.removeFirst();
            produced.signalAll();
            return first;
        } finally {
            lock.unlock();
        }
    }
}
