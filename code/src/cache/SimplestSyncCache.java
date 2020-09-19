package cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 给HashMap类型的缓存加上synchronized关键字
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 8:25
 */
public class SimplestSyncCache {

    private final HashMap<String, Integer> cache  = new HashMap<>();

    public synchronized Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if (result == null) {
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

}
