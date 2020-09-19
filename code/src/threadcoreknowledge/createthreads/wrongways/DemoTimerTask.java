package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
* @Description: 定时器创建线程
* @Param:
* @return:
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/8 20:09
*/
public class DemoTimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
