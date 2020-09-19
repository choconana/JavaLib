package singleton;

/**
 * @Description: 饿汉式（静态代码块）[可用]
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 22:41
 */
public class HungeryOfStaticBlock {
    private final static HungeryOfStaticBlock INSTANCE;

    static {
        INSTANCE = new HungeryOfStaticBlock();
    }

    private HungeryOfStaticBlock() {

    }

    public static HungeryOfStaticBlock getInstance() {
        return INSTANCE;
    }
}
