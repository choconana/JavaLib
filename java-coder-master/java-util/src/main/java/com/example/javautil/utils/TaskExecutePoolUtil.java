package com.example.javautil.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class TaskExecutePoolUtil {

    /**
     * 统计相关的定时任务,定时任务调度相关
     */
    public static ExecutorService STATISTICS_POOL = new ThreadPoolExecutor(8, 20,
            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("statistics_pool_%d").build());

    /**
     * 主流程之类的分支操作
     */
    public static ExecutorService BRANCH_POOL = new ThreadPoolExecutor(4, 20,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("branch_pool_%d").build());

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            STATISTICS_POOL.shutdownNow();
        }));
    }


    /**
     * 提交任务
     *
     * @param runnable
     */
    public static void submit(Runnable runnable) {
        STATISTICS_POOL.submit(new TraceNoWrapper(runnable));
    }

    /**
     * 指定线程池提交任务
     *
     * @param executorService
     * @param runnable
     */
    public static void submit(ExecutorService executorService, Runnable runnable) {
        executorService.submit(new TraceNoWrapper(runnable));
    }

    public static <V> Future<V> submit(ExecutorService executorService, Callable<V> callable) {
        return executorService.submit(callable);
    }


}
