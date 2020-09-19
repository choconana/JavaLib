package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 演示重排序现象，多次重复，“直到达到某个条件才停止”，测试小概率事件
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 15:11
 */
public class OutOfOrderExecution {

    private volatile static int x = 0, y = 0;
    private volatile static int a = 0, b = 0;



    public static void main(String[] args) throws InterruptedException {

        int i = 0;

        for (; ;) {
            i++;
            x=0;
            y=0;
            a=0;
            b=0;

            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });
            one.start();
            two.start();
            // 执行完这行代码才能去执行await()
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次（" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
