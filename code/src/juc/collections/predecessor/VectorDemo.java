package juc.collections.predecessor;

import java.util.Vector;

/**
 * @Description: 演示Vector，主要是看Vector源码，可以当做AarryList使用
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/24 17:29
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        String str = vector.set(0, "test1");
        System.out.println(str + " : " + vector.get(0));
    }
}
