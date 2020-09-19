package generalpractice;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 19:47
 */
public class Guanglianda18 {
    public static final String FOO = "foo";

    public static void main(String[] args) {
        Guanglianda18 b = new Guanglianda18();
        Sub s = new Sub();
        System.out.print(Guanglianda18.FOO);
        System.out.print(Sub.FOO);
        System.out.println(b.FOO);
        System.out.println(s.FOO);
        System.out.println(((Guanglianda18) s).FOO);
    }
}

class Sub extends Guanglianda18 {
    public static final String FOO = "bar";
}
