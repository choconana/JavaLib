package cache;

import cache.computale.Computable;
import cache.computale.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 使用ConcurrentHashMap作为缓存
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 10:32
 */
public class ConcurrentCache<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap();

    private final Computable<A, V> c;

    public ConcurrentCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            // 线程A还没算好，线程B想要获取相同的值也为null，便进入这一步，进行重复的计算
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ConcurrentCache<String, Integer> expensiveComputer = new ConcurrentCache<>(new ExpensiveFunction());
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
