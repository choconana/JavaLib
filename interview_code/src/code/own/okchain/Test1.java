package code.own.okchain;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/17 16:15
 */
public class Test1 implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Test1());
        thread.start();
        Thread.sleep(10000);
    }

    @Override
    public void run() {
        for(;;) {
            System.out.println(System.currentTimeMillis());
        }
    }
}
