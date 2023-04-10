package cn.gaoh.thread.gencon;

/**
 * @Description: 生产消费接口
 * @Author: gaoh
 * @Date: 2021/3/9 15:06
 * @Version: 1.0
 */
public interface ProCon<T> {
    /**
     * 生产
     *
     * @param val
     */
    void put(T val);

    /**
     * 消费者
     *
     * @return
     */
    T get();
}
