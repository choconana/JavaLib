package jvmspace.polymorphic;

/**
 * @author hezhidong
 * @date 2023/1/14 下午1:13
 * @description 单分派和多分派演示
 */
public class SingleAndMultiDispatch {

    static class QQ {}
    static class WX {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father chooses qq");
        }

        public void hardChoice(WX arg) {
            System.out.println("father chooses wx");
        }
    }

    public static class Son extends Father {
        @Override
        public void hardChoice(QQ arg) {
            System.out.println("son chooses qq");
        }

        @Override
        public void hardChoice(WX arg) {
            System.out.println("son chooses wx");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new WX());
        son.hardChoice(new QQ());
    }
}
