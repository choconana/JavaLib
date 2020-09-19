package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 演示用volatile的局限性：part2
 * 陷入阻塞时，volatile无法停止线程
 * 此例中，生产者的生产速度很快，消费者的消费速度慢，
 * 然后阻塞队列满了后，生产者会阻塞，等待消费者进一步消费。
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/9 18:00
 */
public class WrongWayVolatileCannotStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        // 生产者先生产一部分
        Thread.sleep(1000);
        // 1s后消费者开始消费
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");
        // 一旦消费者不要更多数据，此时应当让生产者也停下来
        producer.canceled = true;
        System.out.println("通知生产者停止生产");
    }
}

class Producer implements Runnable {

    BlockingQueue storage;

    public volatile boolean canceled = false;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放到仓库中");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者停止运行");
        }
    }
}

class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}