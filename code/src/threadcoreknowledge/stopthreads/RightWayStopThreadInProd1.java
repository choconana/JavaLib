package threadcoreknowledge.stopthreads;

/**
 * @Description: 最佳实践：catch InterruptedException之后
 * 的优先选择：在方法签名中抛出异常
 * @Param:
 * @return:
 * @Author: hezhidong
 * @Mail: zdhe9535@163.com
 * @Date: 2020/4/9 12:39
 */


public class RightWayStopThreadInProd1 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 保存日志、停止程序
                System.out.println("保存日志");
                e.printStackTrace();
                break;
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        // 若在方法内try/catch而不是用方法签名，中断信息会被吞掉
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd1());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
