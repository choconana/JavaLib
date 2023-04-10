package cn.gaoh.thread.gencon;

import java.util.LinkedList;

/**
 * @Description:
 * @Author: gaoh
 * @Date: 2021/3/7 21:03
 * @Version: 1.0
 */
public class ProducedConsumptionSync<T> implements ProCon<T> {

    //存放相关数据
    private final LinkedList<T> lists = new LinkedList<>();

    /**
     * 生产者
     *
     * @param val
     */
    @Override
    public synchronized void put(T val) {
        int max = 10;
        while (lists.size() == max) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(val);
        this.notifyAll();
    }

    /**
     * 消费者
     *
     * @return
     */
    @Override
    public synchronized T get() {
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            return lists.removeFirst();
        } finally {
            this.notifyAll();
        }
    }
}
