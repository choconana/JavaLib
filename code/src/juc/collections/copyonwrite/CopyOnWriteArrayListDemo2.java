package juc.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/25 16:56
 */
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        System.out.println(list);
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);

        Iterator<Integer> itr2 = list.iterator();
        itr1.forEachRemaining(System.out::println);
        itr2.forEachRemaining(System.out::println);
    }
}
