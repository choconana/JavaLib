package threadcoreknowledge.sixstatus;

/**
 * @Description: 展示线程的NEW、RUNNABLE、TERMINATED三种状态
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/9 23:19
 */
public class NewRunnableTerminated implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // NEW
        System.out.println(thread.getState());
        thread.start();
        // RUNNABLE
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // RUNNABLE
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TERMINATED
        System.out.println(thread.getState());
    }
}
