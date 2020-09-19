package cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 最简单的缓存形式：HashMap
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/30 23:40
 */
public class SimplestCache {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        // 先检查cache中有没有保存过之前的计算结果
        if (result == null) {
            // 如果缓存中找不到，那么需要现在计算一下结果，并且保存到cache中
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        SimplestCache simplestCache = new SimplestCache();
        System.out.println("开始计算");
        int result = simplestCache.computer("13");
        System.out.println("第一次计算结果: " + result);
        result = simplestCache.computer("13");
        System.out.println("第二次计算结果: " + result);
    }

}
