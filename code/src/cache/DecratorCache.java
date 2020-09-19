package cache;

import cache.computale.Computable;
import cache.computale.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用装饰者模式，给计算器自动添加缓存功能
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 8:57
 */
public class DecratorCache<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap();

    private final Computable<A, V> c;

    public DecratorCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        DecratorCache<String, Integer> expensiveComputer = new DecratorCache<>(new ExpensiveFunction());
        System.out.println("开始计算");
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
