package threadcoreknowledge.stopthreads;

/**
* @Description: 错误的停止线程的方法，会导致线程运行一半突然停止，
 * 因此没办法完成一个基本单位的操作(一个连队)，会造成脏数据(有的连队多领有的连队少领装备)
* @Param:
* @return:
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/9 17:01
*/
public class StopThread implements Runnable {

    @Override
    public void run() {
        // 模拟指挥军队：一共有个连队，每隔连队10人，以连队为单位，发放弹药，叫到号的士兵前去领取
        for (int i = 0; i < 5; ++i) {
            System.out.println("连队" + i + "开始领取武器");
            for (int j = 0; j < 10; ++j) {
                System.out.println("连队" + i + "的第" + j + "号士兵领取武器");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "已经领取完毕\n========");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
