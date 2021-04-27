package com.zor.basic.highconcurrency.threadpool;

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

    public static void main(String[] args) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 5, 1, unit, workQueue);


        // 核心线程和非核心线程不分类，一视同仁
        // 会出现一个问题，后来的任务先执行，因为先来的一部分会到队列排队
        for (int i = 0; i < 5; i++) {
            Command command = new Command(i);
            // submit会有返回值
            Future<?> submit = threadPoolExecutor.submit(command);
            // execute没有返回值
            threadPoolExecutor.execute(command);
        }

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
