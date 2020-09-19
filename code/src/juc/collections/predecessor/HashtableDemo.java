package juc.collections.predecessor;

import java.util.Hashtable;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/24 17:34
 */
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("0", "1");
        System.out.println(hashtable.get("0"));
    }
}
