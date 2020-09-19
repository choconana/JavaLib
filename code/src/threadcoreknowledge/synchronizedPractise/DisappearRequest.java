package threadcoreknowledge.synchronizedPractise;

/**
 * @Description 消失的请求数
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/10 16:50
 */
public class DisappearRequest implements Runnable {

//    static DisappearRequest instance = new DisappearRequest();

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        DisappearRequest instance = new DisappearRequest();
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; ++j) {
            i++;
//            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
     }
}
