package juc.concurrencytools.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:    用1000个线程分别打印出日期信息，用线程池来执行，
 *                  用静态变量消除重复创建而带来的资源消耗问题，并加锁防止线程安全问题
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/28 9:14
 */
public class ThreadLocalNormalUsage04 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
    static int i = 0;

    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        String s = null;
        synchronized (ThreadLocalNormalUsage04.class) {
            s = dateFormat.format(date);
            System.out.println(ThreadLocalNormalUsage04.class + ": "+ i++);
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage04().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }
}
