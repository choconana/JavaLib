package threadcoreknowledge.createthreads;

/**
 * @Description: 用Runnable方式创建线程
 * @Param:
 * @return:
 * @Author: hezhidong
 * @Mail: zdhe9535@163.com
 * @Date: 2020/4/8 17:55
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
