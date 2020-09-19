package cache;

import cache.computale.Computable;
import cache.computale.ExpensiveFunction;
import cache.computale.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description: 使用ConcurrentHashMap的putIfAbsent原子操作
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 12:03
 */
public class AtomicFutureCache<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap();

    private final Computable<A, V> c;

    public AtomicFutureCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "进入缓存机制");
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable callable = () -> {
                    return c.compute(arg);
                };

                FutureTask futureTask = new FutureTask(callable);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    System.out.println(Thread.currentThread().getName() + "从FutureTask调用了计算函数");
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                // 每次进入捕获的异常就要移除之前的Future，防止缓存污染
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算出错，请重试");
                cache.remove(arg);
            }
        }
    }



    public static void main(String[] args) throws Exception {
        AtomicFutureCache<String, Integer> expensiveComputer = new AtomicFutureCache<>(new MayFail());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第一次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("667");
                System.out.println("第二次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-2").start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第三次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-3").start();
    }

}
