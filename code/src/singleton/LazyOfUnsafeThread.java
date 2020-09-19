package singleton;

/**
 * @Description: 懒汉式（线程不安全）[不可用]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 22:47
 */
public class LazyOfUnsafeThread {
    private static LazyOfUnsafeThread instance;

    private LazyOfUnsafeThread() {

    }

    public static LazyOfUnsafeThread getInstance() {
        // 只会生成一个实例
        if (instance == null) {
            instance = new LazyOfUnsafeThread();
        }
        return instance;
    }
}
