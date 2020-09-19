package threadcoreknowledge.stopthreads;

/**
 * @Description: run方法内没有sleep或wait方法是停止线程
 * @Param:
 * @return:
 * @Author: hezhidong
 * @Mail: zdhe9535@163.com
 * @Date: 2020/4/9 11:26
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        // 如果调用interrupt()前没有调用sleep()，则线程会立刻收到中断通知
        Thread.sleep(1000);
        thread.interrupt();
    }
}
