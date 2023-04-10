package com.example.javautil.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class ThreadUtils {

    private static volatile ExecutorService executor;

    private static ExecutorService getExecutor() {

        if (executor == null) {
            log.warn("第一次获取executor");
            synchronized (ExecutorService.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(
                            5,
                            10,
                            60,
                            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                            (r) -> {
                                Thread thread = new Thread(r);
                                thread.setName("user Thread");
                                return thread;
                            },
                            new ThreadPoolExecutor.AbortPolicy());
                    log.info("获取到线程池");
                }
            }
        }
        return executor;
    }


    public static void addTask(Runnable thread) {
        getExecutor().submit(thread);
    }


    public static <V> Future addTask(Callable<V> task) {
        Future<V> submit = getExecutor().submit(task);
        return submit;
    }
}



