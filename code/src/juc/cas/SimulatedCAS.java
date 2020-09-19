package juc.cas;

/**
 * @Description: 模拟CAS操作，等价代码
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 14:38
 */
public class SimulatedCAS {
    private volatile int value;

    // 对应一条CPU原子级别的指令
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
