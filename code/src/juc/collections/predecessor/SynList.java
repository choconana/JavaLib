package juc.collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 演示Collection.synchronizedList(new AarryList<E>())
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/24 18:02
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(6);
        System.out.println(list.get(0));
    }
}
