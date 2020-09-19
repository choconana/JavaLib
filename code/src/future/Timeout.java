package future;

import java.util.concurrent.*;

/**
 * @Description: 演示get的超时方法，需要注意超时后处理，调用future.cancel()。
 *  演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/30 10:11
 */
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");

    ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                // 兜底策略
                return new Ad("中断时候的默认广告");
            }
            return new Ad("旅游订票找xxx");
        }
    }

    public void printAd() {
        Future<Ad> future = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            System.out.println("超时，为获取到广告");
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果："+ cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }
}
