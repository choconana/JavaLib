package future;

/**
 * @Description: 在run方法中无法抛出checked Exception
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/28 11:11
 */
public class RunnableCantThrowException {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
