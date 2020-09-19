package threadcoreknowledge.stopthreads;

/**
 * @Description: 最佳实践2：在catch子语句中调用
 * Thread.currentThread().interrupt()来恢复设置中断状态，
 * 以便于在后续的执行中，依然能够坚持到刚才发生的中断
 * @Param:
 * @return:
 * @Author: hezhidong
 * @Mail: zdhe9535@163.com
 * @Date: 2020/4/9 12:39
 */


public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
