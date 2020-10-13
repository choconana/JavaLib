package Code.other.haoweilai;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/12 13:43
 */
public class test16 {
    public static void main(String[] args) {
        Class clazz = Class.class.getClass();
        Object object = Object.class.getClass();
        System.out.println(clazz == object);
        System.out.println(Class.class.getClass() == Object.class.getClass());
    }
}
