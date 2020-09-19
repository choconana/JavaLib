package juc.threadpool;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/17 19:54
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
