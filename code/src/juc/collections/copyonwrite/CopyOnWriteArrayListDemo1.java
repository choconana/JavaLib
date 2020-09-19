package juc.collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 演示CopyOnWriteArrayList可以在迭代的过程中修改数组内容，但是ArrayList不行
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/25 16:28
 */
public class CopyOnWriteArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is " + list);
            String next = iterator.next();
            System.out.println(next);

            if (next.equals("b")) {
                list.remove("e");
            }
            if (next.equals("c")) {
                list.add("c found");
            }
        }
    }
}
