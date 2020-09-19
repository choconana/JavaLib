package generalpractice;

import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 19:21
 */
public class guanglianda {
    public static void main(String[] args) {
//        ClassA a = new ClassB();
//        InterfaceA b = new ClassB();
//        a.g();
//        b.f();
//        method1(new ClassB());
//        method1(new ClassA());
        ClassB b = new ClassB();
        b.f();
    }
    public static void method1(ClassA a) {
        if (a instanceof ClassA) {
            System.out.println("A");
        } else if(a instanceof ClassB) {
            System.out.println("B");
        }
    }
}

interface InterfaceA {
    int max = 100;
    void f();
}

class ClassA {
    void g() {};
}

class ClassB extends ClassA implements InterfaceA {


    public ClassB() {
        super();
    }

    void g() {
        System.out.println(max);
    }

    public void f() {

        System.out.println("1231241");
        System.out.println("523452");
        Scanner cin = new Scanner(System.in);
        String str1 = cin.nextLine();

//        System.out.println(" " + max);
    }
}