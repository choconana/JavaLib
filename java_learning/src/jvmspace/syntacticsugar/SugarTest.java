package jvmspace.syntacticsugar;

import java.util.Arrays;
import java.util.List;

/**
 * @author hezhidong
 * @date 2023/1/18 下午8:07
 * @description 语法糖演示，自动装箱、自动拆箱、遍历循环foreach
 */
public class SugarTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
