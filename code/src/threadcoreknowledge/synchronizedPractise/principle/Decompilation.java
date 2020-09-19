package threadcoreknowledge.synchronizedPractise.principle;

/**
 * @Description: 反编译字节码
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 23:32
 */
public class Decompilation {
    private Object object = new Object();

    public void insert(Thread thread) {
        synchronized (object) {

        }
    }
}
