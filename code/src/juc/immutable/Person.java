package juc.immutable;

/**
 * @Description: 不可变的对象，演示其他类无法修改这个对象，public也不行
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 18:28
 */
public final class Person {
    final int age = 18;
    final String name = "Alice";
    String nickname = "angel";
}
