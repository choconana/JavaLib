package singleton;

/**
 * @Description: 双重检查 [面试推荐]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 23:10
 */
public class LazyOfDoubleChecked {
    private volatile static LazyOfDoubleChecked instance;

    private LazyOfDoubleChecked() {

    }

    public static LazyOfDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (LazyOfDoubleChecked.class) {
                // 实例化需要一段时间，如果在实例化期间，也就是该类还未被完全实例化，此时instance == null，因此其他线程会通过第一个判断
                // 因此在加锁后还需要第二次判断
                if (instance == null) {
                    instance = new LazyOfDoubleChecked();
                }
            }
        }
        return instance;
    }
}
