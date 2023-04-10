package jvmspace.polymorphic;

/**
 * @author hezhidong
 * @date 2023/1/14 下午12:37
 * @description 字段没有多态性演示
 */
public class FieldHasNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am father, I have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        @Override
        public void showMeTheMoney() {
            System.out.println("I am son, I have $" + money);
        }
    }

    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("This guy has $" + guy.money);
    }
}
