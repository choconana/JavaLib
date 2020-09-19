package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 多个运动员等待起跑信号，起跑后，工作人员在终点计时，等待最后一名运动员到达终点后，宣布本次比赛结束
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/6/5 21:20
 */
public class CountDownLatchDemo3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("No." + no + "开始跑步了");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + "到达终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        System.out.println("裁判检查工作中...");
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始");
        begin.countDown();
        end.await();
        System.out.println("本次比赛结束");
        service.shutdownNow();
    }
}
