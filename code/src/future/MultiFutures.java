package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示鼻梁提交任务时，用List来批量接受结果
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/29 19:15
 */
public class MultiFutures {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }
        Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = futures.get(i);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
