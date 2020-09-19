package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 用wait/notify来实现
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/12 19:40
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Producer producer1 = new Producer(storage);
        Producer producer2 = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        new Thread(producer, "producer").start();
        new Thread(producer1, "producer1").start();
        new Thread(producer2, "producer2").start();
        new Thread(consumer, "consumer").start();
    }
}


class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 300; ++i) {
            storage.take();
        }
    }
}

class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void put() {
        // 不能用if，否则存在多个生产者时，会造成库存积压
        // 因为if只能判断一次，被唤醒后，不再去判断库存是否已满，而是继续生产
        while (storage.size() == maxSize) {
            try {
                System.out.println(Thread.currentThread().getName() + "进入waiting状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品。");
        notify();
    }

    public synchronized void take() {
        // 不能用if，否则存在多个消费者时，会造成一些消费者取到空
        while (storage.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "进入waiting状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + ", 现在仓库还剩下" + storage.size() + "个产品。");
        notify();
    }
}

