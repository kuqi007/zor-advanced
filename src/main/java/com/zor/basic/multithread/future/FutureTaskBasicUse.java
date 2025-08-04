package com.zor.basic.multithread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 当一个线程需要等待另一个线程把某个任务执行完后它才能继续执行，此时可以使用FutureTask
 */
public class FutureTaskBasicUse {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    result += i;
                    System.out.println("result" + result);
                }

                return result;
            }
        });
        //
        //Thread computeThread = new Thread(futureTask);
        //computeThread.setDaemon(true);
        //computeThread.start();

        futureTask.run();


        Thread otherThread = new Thread(() -> {
            System.out.println("other task is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        otherThread.start();
        //System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());
    }
}
