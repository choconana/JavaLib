package threadcoreknowledge.stopthreads;

/**
* @Description: 如果while里面放try/catch，那么中断会失效
* @Param:
* @return:
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/9 12:12
*/
public class CannotInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
