package threadcoreknowledge.synchronizedPractise.properties;

/**
 * @Description: 可重入粒度测试：情况1，递归调用本方法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 22:01
 */
public class SynchronizedRecursion {

    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion synchronizedRecursion = new SynchronizedRecursion();
        synchronizedRecursion.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1，a = " + a);
        if (a == 0) {
            a++;
            method1();
        }
    }
}
