package threadcoreknowledge.stopthreads;

/** 
* @Description: 在线程执行迭代程序时，每次迭代都会调用sleep或wait等方法
* @Param: 
* @return: 
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/9 12:04
*/


public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
