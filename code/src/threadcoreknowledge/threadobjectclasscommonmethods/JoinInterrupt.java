package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 演示join期间被中断的效果
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 15:36
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1 finished");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
                e.printStackTrace();
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
            thread1.interrupt();
            e.printStackTrace();
        }
//        System.out.println("子线程运行完毕");
    }
}
