package generalpractice;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 19:33
 */
public class Guanglianda10 {
    private static Integer age;

    public static void grow(int age) {
        age++;
    }

    public static void main(String[] args) throws NullPointerException {
        grow(age);
        System.out.println(age);
    }
}
