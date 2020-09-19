package juc.collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 组合操作并不保证线程安全
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/24 23:18
 */
public class OptionsNotSafe implements Runnable {
    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                boolean b = scores.replace("小明", score, newScore);
                if (b) break;
            }
            // 组合使用get和put会引发线程安全问题
//            scores.put("小明", newScore);
        }
    }
}
