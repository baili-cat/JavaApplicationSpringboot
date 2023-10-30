package com.example.test.xskytest.apacheDubboService.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    public static ThreadPoolExecutor threadPool;

    /**
     * 无返回值直接执行
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }


    /**
     * dcs获取线程池
     */
    public static ThreadPoolExecutor getThreadPool() {

        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(3, 5, 20,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPool;
            }
        }
    }
}
