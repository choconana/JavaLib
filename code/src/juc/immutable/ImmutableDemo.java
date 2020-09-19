package juc.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 一个属性是对象，但是整体不可变，其他类无法修改set里面的数据
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 23:10
 */
public class ImmutableDemo {
    private final Set<String> students = new HashSet<>();

    public ImmutableDemo() {
        students.add("李白");
        students.add("李商隐");
    }

    public boolean isStudents(String name) {
        return students.contains(name);
    }
}
