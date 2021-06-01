package com.zor.basic.highconcurrency.threadpool;

import java.text.NumberFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuqiqi03
 * @date 2021/4/26
 */
public class ThreadPoolBasicUse {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
            1, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) {

        // 开启所有核心线程，线程预热
        threadPoolExecutor.prestartAllCoreThreads();
        // 开启一个核心线程
        //threadPoolExecutor.prestartCoreThread();

        // 核心线程和非核心线程不分类，一视同仁
        // 会出现一个问题，后来的任务先执行，因为先来的一部分会到队列排队
        for (int i = 0; i < 20; i++) {
            Command command = new Command(i);
            // submit会有返回值
            //Future<?> submit = threadPoolExecutor.submit(command);
            // execute没有返回值
            threadPoolExecutor.execute(command);
        }

        // 模拟动态控制线程池
        int corePoreSize = 10;
        int maxPoolSize = 20;
        threadPoolExecutor.setCorePoolSize(corePoreSize);
        threadPoolExecutor.setMaximumPoolSize(maxPoolSize);


        // 模拟监控线程池负载情况
        Thread command = new Thread(() -> {
            while (true) {
                getLoadPercent();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolExecutor.submit(command);


    }


    public static Double getLoadPercent() {
        int activeCount = threadPoolExecutor.getActiveCount();
        int maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        System.out.println("线程池负载为：" + 1.0 * activeCount / maximumPoolSize * 100 + "%");
        return 1.0 * activeCount / maximumPoolSize;
    }

    static class Command implements Runnable {

        private final Integer name;

        public Command(int name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
