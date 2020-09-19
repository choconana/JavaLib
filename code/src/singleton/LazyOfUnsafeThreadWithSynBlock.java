package singleton;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Description: 懒汉式（线程不安全，同步代码块）[不推荐]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 23:01
 */
public class LazyOfUnsafeThreadWithSynBlock {
    private static LazyOfUnsafeThreadWithSynBlock instance;

    private LazyOfUnsafeThreadWithSynBlock() {

    }

    public static LazyOfUnsafeThreadWithSynBlock getInstance() {
        if (instance == null) {
            synchronized (LazyOfUnsafeThreadWithSynBlock.class) {
                instance = new LazyOfUnsafeThreadWithSynBlock();
            }
        }
        return instance;
    }
}
