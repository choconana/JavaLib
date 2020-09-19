package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示一个Future的使用方法, lambda形式
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/28 16:40
 */
public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };
        Future<Integer> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
