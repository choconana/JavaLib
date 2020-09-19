package juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/19 10:55
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x * y, 1);
//        accumulator.accumulate(1);
//        accumulator.accumulate(3);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // range(x, y)表示的范围为[x, y)
        IntStream.range(1, 10).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        executor.shutdown();
        while(!executor.isTerminated()) {

        }
        System.out.println(accumulator.getThenReset());
    }

}
