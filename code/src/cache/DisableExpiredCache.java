package cache;

import cache.computale.Computable;
import cache.computale.MayFail;

import java.util.concurrent.*;

/**
 * @Description: 出于安全性考虑，缓存需要设置有效期。到期自动失效，否则如果缓存一直不失效，会带来缓存不一致的问题
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/1 9:42
 */
public class DisableExpiredCache<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap();

    private final Computable<A, V> c;

    public DisableExpiredCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable callable = () -> {
                    return c.compute(arg);
                };

                FutureTask<V> futureTask = new FutureTask<>(callable);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    // （清空后）第一次future为空，线程A进来，赋值后不为空，线程B跳过计算直接去获取值；
                    future = futureTask;
                    System.out.println(Thread.currentThread().getName() + "从FutureTask调用了计算函数");
                    futureTask.run();
                }
            }
            try {
                // 线程B在这里等待获取futureTask执行完毕的返回值
                return future.get();
            } catch (CancellationException e) {
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

    public final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    public V compute(A arg, long expireTime, TimeUnit unit) throws InterruptedException {
        if (expireTime > 0) {
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);
                }
            }, expireTime, unit);
        }
        return compute(arg);
    }

    private synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            // 时间设置很短，future还未完成获取缓存的任务，需要取消掉
            if (!future.isDone()) {
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }
    }

    public static void main(String[] args) throws Exception {
        DisableExpiredCache<String, Integer> expensiveComputer = new DisableExpiredCache<>(new MayFail());
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666", 5, TimeUnit.SECONDS);
                System.out.println("第一次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第三次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-3").start();
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("667");
                System.out.println("第二次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-2").start();

        Thread.sleep(6000);
        new Thread(() -> {
            try {
                Integer result = expensiveComputer.compute("666");
                System.out.println("第四次计算结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-4").start();
    }

}
