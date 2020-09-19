package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 通过讲解join原理，分析出join的代替写法
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 16:24
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }
        });
        thread1.start();
        System.out.println("开始等待子线程运行完毕");
//        thread1.join();
        // join的等价写法
        synchronized (thread1) {
            thread1.wait();
        }
        System.out.println("所有子线程执行完毕");
    }

}
