package threadcoreknowledge.createthreads;

/** 
* @Description: 同时使用Runnable和Thread两种实现线程方式
* @Param: 
* @return: 
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/8 19:13
*/


public class BothRunnableAndThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
