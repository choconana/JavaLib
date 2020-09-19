package singleton;

/**
 * @Description: 静态内部类方法[推荐]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 23:28
 */
public class StaticInnerClass {

    private StaticInnerClass() {

    }

    public static StaticInnerClass getInstance() {
        return SingletonInstance.INSTANCE;
    }

    private static class SingletonInstance {
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }
}
