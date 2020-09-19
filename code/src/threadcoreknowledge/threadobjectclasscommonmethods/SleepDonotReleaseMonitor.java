package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 展示线程sleep的时候不释放synchronized的monitor，等sleep时间到了以后，正常结束后才释放锁
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 10:38
 */
public class SleepDonotReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        SleepDonotReleaseMonitor sleepDonotReleaseMonitor = new SleepDonotReleaseMonitor();
        new Thread(sleepDonotReleaseMonitor).start();
        new Thread(sleepDonotReleaseMonitor).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + "获取到了monitor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "退出同步代码块");
    }


}
