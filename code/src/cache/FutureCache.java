package cache;

import cache.computale.Computable;
import cache.computale.ExpensiveFunction;
import cache.computale.MayFail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description: 利用Future避免重复计算
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 11:04
 */
public class FutureCache<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new HashMap();

    private final Computable<A, V> c;

    public FutureCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入缓存机制");
        Future<V> result = cache.get(arg);
        if (result == null) {
            Callable callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> futureTask = new FutureTask<>(callable);
            result = futureTask;
            cache.put(arg, futureTask);
            System.out.println(Thread.currentThread().getName() + "从FutureTask调用了计算函数");
            futureTask.run();
        }
        return result.get();
    }

    public static void main(String[] args) throws Exception {
        FutureCache<String, Integer> expensiveComputer = new FutureCache<>(new ExpensiveFunction());
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
