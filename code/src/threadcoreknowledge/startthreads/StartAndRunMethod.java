package threadcoreknowledge.startthreads;

/** 
* @Description: 对比start和run两种启动线程的方式
* @Param: 
* @return: 
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/8 22:11
*/
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}
