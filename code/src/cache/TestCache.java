package cache;

import cache.computale.ExpensiveFunction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/1 12:28
 */
public class TestCache {
    static DisableExpiredCache<String, Integer> expensiveComputer = new DisableExpiredCache<>(new ExpensiveFunction());

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1200; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
        service.shutdown();
        while (!service.isTerminated()) {

        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }
}
