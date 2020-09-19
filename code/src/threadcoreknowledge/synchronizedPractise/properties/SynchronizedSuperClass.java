package threadcoreknowledge.synchronizedPractise.properties;

/**
 * @Description: 可重入粒度测试：情况3，调用父类的方法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 22:10
 */
public class SynchronizedSuperClass {

    public synchronized void method() {
        System.out.println("我是父类method");
        SynchronizedOtherMethod s = new SynchronizedOtherMethod();
    }
}

class TestClass extends SynchronizedSuperClass {

    @Override
    public synchronized void method() {
        System.out.println("我是子类method");
        super.method();
        SynchronizedOtherMethod s = new SynchronizedOtherMethod();
        s.method1();
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.method();
    }
}
