package code.own.jd;

import java.util.ArrayList;
import java.util.HashSet;

public class Main1 {
    public static void main(String[] args) {
        HashSet<Character> set = new HashSet<>();
        ArrayList<Character> list = new ArrayList<>();
        list.add('b');
        list.add('b');

        System.out.println(list.size());
    }
}
