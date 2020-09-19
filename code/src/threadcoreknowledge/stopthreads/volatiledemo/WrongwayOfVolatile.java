package threadcoreknowledge.stopthreads.volatiledemo;

/** 
* @Description: 演示用volatile的局限：part1 看似可行
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/9 17:34
*/
public class WrongwayOfVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongwayOfVolatile wrongwayOfVolatile = new WrongwayOfVolatile();
        Thread thread = new Thread(wrongwayOfVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongwayOfVolatile.canceled = true;
    }
}
