package cache.computale;

import java.io.IOException;

/**
 * @Description: 耗时计算的实现类，有概率计算失败
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 23:19
 */
public class MayFail implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("read exception");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
