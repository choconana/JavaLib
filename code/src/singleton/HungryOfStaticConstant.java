package singleton;

/**
 * @Description: 饿汉式（静态常量）[可用]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 22:36
 */
public class HungryOfStaticConstant {

    private final static HungryOfStaticConstant INSTANCE = new HungryOfStaticConstant();

    private HungryOfStaticConstant() {

    }

    public static HungryOfStaticConstant getInstance() {
        return INSTANCE;
    }
}
