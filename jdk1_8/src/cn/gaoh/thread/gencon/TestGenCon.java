package cn.gaoh.thread.gencon;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.CountDownLatch;


/**
 * @Description:
 * @Author: gaoh
 * @Date: 2021/3/7 21:10
 * @Version: 1.0
 */
@Slf4j
public class TestGenCon {
    public static void main(String[] args) {
//        proCon(new ProducedConsumptionLock<>());
        proCon(new ProducedConsumptionSync<>());

        HashSet<Object> set1 = new HashSet<>();
        LinkedHashSet<Object> set2 = new LinkedHashSet<>();
        set1.add("");
        set2.add("");
    }

    private static void proCon(ProCon<String> obj) {
        int count = 10;
        CountDownLatch downLatch = new CountDownLatch(count);
        long timeMillis = System.currentTimeMillis();
        //消费者线程
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    log.debug(obj.get());
                }
                downLatch.countDown();
            }, "c" + (i + 1)).start();
        }
        //生产者线程
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    obj.put(Thread.currentThread().getName() + "-" + (j + 1));
                }
            }, "p" + (i + 1)).start();
        }
        try {
            downLatch.await();
            log.debug("结束！耗时：{}", System.currentTimeMillis() - timeMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
