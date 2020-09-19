package juc.immutable;

/**
 * @Description: final修饰方法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 22:32
 */
public class FinalMethodDemo {
    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}

class subClass extends FinalMethodDemo {
    @Override
    public void drink() {
        super.drink();
        eat();
    }

    // final修饰的方法不允许被子类重写，重名的方法也不允许有
//    public void eat() {
//
//    }

    // 子类可以有与父类重名的静态方法，但不是重写父类的方法
    public static void sleep() {

    }
}
