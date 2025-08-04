package com.zor.basic.multithread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 《并发编程的艺术》P223-P224源码：
 * 当两个线程试图执行同一个任务时候，如果Thread1执行1.3后Thread2执行2.1，那么接下来Thread2将在2.2等待，
 * 直到Thread1执行完1.4后Thread2才能从2.2（FutureTask.get()）返回
 */
public class FutureTaskDemo1 {
    private final ConcurrentHashMap<String, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        FutureTaskDemo1 futureTaskDemo1 = new FutureTaskDemo1();
    }

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);//1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() {
                        return taskName;
                    }
                };

                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);//1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run();//1.4执行任务
                }
            }

            try {
                return future.get();//1.5,2.2线程在此等待任务执行完成
            } catch (Exception e) {
                taskCache.remove(taskName, future);
            }
        }

    }
}
