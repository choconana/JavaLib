package jvmspace.polymorphic;

/**
 * @author hezhidong
 * @date 2023/1/13 下午10:39
 * @description 动态分派演示
 */
public class DynamicDispatch {

    static abstract class Human {
        protected  abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man says hello");
        }
    }

     static class Woman extends Human {

         @Override
         protected void sayHello() {
             System.out.println("woman says hello");
         }
     }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();

    }
}
