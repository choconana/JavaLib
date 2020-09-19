package future;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.*;

/**
 * @Description: 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：
 *  并不是一产生异常就抛出，直到我们get执行时，才会抛出
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/7/30 9:59
 */
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future future = service.submit(new CallableTask());
        try {
            // for循环时，异常已经产生
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.println(i);
            }
            // 只能直到任务停止，至于是否成功完成并不知道
            System.out.println(future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        }
        service.shutdown();
    }

    static class CallableTask implements Callable {

        @Override
        public Object call() throws Exception {
            throw new Exception("Callable抛出异常");
        }
    }
}
