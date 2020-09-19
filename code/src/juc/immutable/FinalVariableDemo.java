package juc.immutable;

import juc.lock.reentrantlock.GetHoldCount;

/**
 * @Description: 演示final变量
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 20:26
 */
public class FinalVariableDemo {
    private final int a;

    private static final int b;

    void testFinal() {
        System.out.println(b);
        final int c;
        int d;
//        d = c; // Variable 'd' might not have been initialized
//        c = d; // Variable 'd' might not have been initialized
    }

//    public FinalVariableDemo(int a) {
//        this.a = a;
//    }

    {
        a = 7;
    }

    static {
        b = 9;
    }

    public static void main(String[] args) {
        FinalVariableDemo finalVariableDemo = new FinalVariableDemo();
        finalVariableDemo.testFinal();
    }
}
