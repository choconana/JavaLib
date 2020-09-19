package threadcoreknowledge.threadsecurity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 构造函数中新建线程
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 10:20
 */
public class InitEscapeBeczofThreadInConstructor {

    private Map<String, String> states;

    public InitEscapeBeczofThreadInConstructor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "周一");
                states.put("2", "周二");
                states.put("3", "周三");
                states.put("4", "周四");
            }
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        InitEscapeBeczofThreadInConstructor InitEscapeBeczofThreadInConstructor = new InitEscapeBeczofThreadInConstructor();
//        Thread.sleep(1000);
        Map<String, String> states = InitEscapeBeczofThreadInConstructor.getStates();
        System.out.println(states.get("1"));
    }
}
