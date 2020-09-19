package juc.concurrencytools.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:    用两个线程分别打印出日期信息
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/28 9:14
 */
public class ThreadLocalNormalUsage00 {
    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(1007);
                System.out.println(date);
            }
        }).start();
    }
}
