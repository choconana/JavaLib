import java.util.HashMap;

/**
 * @Description: 演示HashMap在多线程情况下造成死循环的情况
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/24 21:49
 */
public class HashMapEndlessLoop {
    // HashMap<>(初始容量, 负载因子(什么时候扩容))
    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 1.5f);

    public static void main(String[] args) {
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread2").start();
    }
}
