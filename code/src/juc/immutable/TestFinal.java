package juc.immutable;

/**
 * @Description: 测试final能否被修改
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 18:30
 */
public class TestFinal {
    public static void main(String[] args) {
        final Person person = new Person();
//        Person = new Person();
        int age = person.age;
        person.nickname = "baby";
    }
}
