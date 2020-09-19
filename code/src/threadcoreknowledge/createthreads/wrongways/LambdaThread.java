package threadcoreknowledge.createthreads.wrongways;

/**
* @Description: lambda表达式创建线程
* @Param:
* @return:
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/8 20:36
*/
public class LambdaThread {
    public static void main(String[] args) {
        new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        ).start();
    }
}
