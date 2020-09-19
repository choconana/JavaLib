package generalpractice;

import java.util.ArrayList;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/27 23:11
 */
public class ArrayListCopy {

    public static void main(String[] args) {
        int[] list = new int[10];
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        System.out.println(list1.get(0));
        for (int i = 0; i < list1.size(); i++) {
            list[i] = list1.get(i);
//            System.out.println(list1.get(i));

        }
        System.out.println(list);
    }
}
