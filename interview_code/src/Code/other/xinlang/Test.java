package Code.other.xinlang;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 16:54
 */
public class Test {
    static class Father {
        static {
            System.out.println("Static Father");
        }
        {
            System.out.println("Non-static Father");
        }
        public Father() {
            System.out.println("Constructor Father");
        }
    }

    static class Son extends Father {
        static {
            System.out.println("Static Son");
        }
        {
            System.out.println("Non-static Son");
        }
        public Son() {
            System.out.println("Construct Son");
        }
    }

    public static void main(String[] args) {
        new Son();
    }
}
