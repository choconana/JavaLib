package threadcoreknowledge.sixstatus;

/**
 * @Description: 展示blocked、waiting、timed_waiting三种线程状态
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/9 23:28
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TIMED_WAITING，因为thread1正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        // BLOCKED，因为thread1还带着syn()的锁处于sleep状态，thread2还拿不到syn()的锁
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // WAITING
        System.out.println(thread1.getState());
    }
}
