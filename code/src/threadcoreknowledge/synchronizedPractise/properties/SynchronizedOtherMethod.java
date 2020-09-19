package threadcoreknowledge.synchronizedPractise.properties;

/**
 * @Description: 可重入粒度测试：情况2，调用类内另外的方法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 22:06
 */
public class SynchronizedOtherMethod {

    public synchronized void method1() {
        System.out.println("我是method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是method2");
    }

    public static void main(String[] args) {
        SynchronizedOtherMethod s = new SynchronizedOtherMethod();
        s.method1();
    }
}
