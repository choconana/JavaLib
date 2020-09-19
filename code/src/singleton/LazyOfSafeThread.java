package singleton;

/**
 * @Description: 懒汉式(线程安全)[不推荐]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 22:55
 */
public class LazyOfSafeThread {
    private static LazyOfSafeThread instance;

    private LazyOfSafeThread() {

    }

    public synchronized static LazyOfSafeThread getInstance() {
        if (instance == null ) {
            instance = new LazyOfSafeThread();
        }
        return instance;
    }
}
