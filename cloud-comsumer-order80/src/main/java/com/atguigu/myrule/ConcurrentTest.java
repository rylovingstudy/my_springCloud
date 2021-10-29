package com.atguigu.myrule;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ren
 * @version 1.0
 * @description: TODO
 * @date 2021/10/29 15:30
 */
public class ConcurrentTest {
    public static final int CORE_POOL_SIZE = 3;
    public static final int MAXIMUM_POOL_SIZE = 5;
    public static final long KEEP_ALIVE_TIME = 200;
    public static final int WORK_QUEUE_SIZE = 10;
    public static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j);
                }
            });
        }

        System.out.println("ok...");
    }
}
