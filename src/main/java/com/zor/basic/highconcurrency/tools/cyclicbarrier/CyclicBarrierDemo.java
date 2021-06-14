package com.zor.basic.highconcurrency.tools.cyclicbarrier;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by kuqi0 on 2021/6/14
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        // 等待所有线程调用await方法，执行barrier action
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("发令枪响了，跑！");
            }
        });
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        /*
         * 如果新建了10个线程，CyclicBarrier是可以复用的
         * 举个例子，我们可以假设CyclicBarrier是一个发令枪，因为资金有限，运动会上发令枪只有一把；
         * 我们现在举办一场特别的运动会，因为赛道有限，10个运动员被迫分为两组，需要等第一组全部出发以后第二组才开始准备
         * 1.假设现在有两组运动员等待发令枪开启比赛，每批有5个运动员（5个线程）。
         * 2.第一组运动员需要相互等待其他运动员准备就绪，当裁判发现所有运动员都准备完毕之后，就举起发令枪，第一组比赛开始；
         * 3.第二组此时开始准备，互相等待本组其他运动员准备就绪，当第二组全体就绪时，裁判举起发令枪，第二组比赛开始
         *
         *
         */
        long prepareTimeGroup1 = 5000;
        System.out.println("第一组准备，准备时间" + prepareTimeGroup1);
        for (int i = 0; i < 5; i++) {
            //executorService.submit(new MyThread(barrier, "运动员" + i + "号"));
            new MyThread(barrier, "运动员" + i + "号", prepareTimeGroup1).start();
        }

        long prepareTimeGroup2 = 10000;
        System.out.println("第二组预准备，准备时间" + prepareTimeGroup2);
        for (int i = 5; i < 10; i++) {
            new MyThread(barrier, "运动员" + i + "号", prepareTimeGroup2).start();
        }
    }


    static class MyThread extends Thread {
        private final CyclicBarrier cyclicBarrier;
        private final String name;
        private final long prepareTimeMill;

        public MyThread(CyclicBarrier cyclicBarrier, String name, long prepareTimeMill) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            this.prepareTimeMill = prepareTimeMill;
        }

        @Override
        public void run() {
            System.out.println(name + "开始准备");
            try {
                sleep(prepareTimeMill);
                System.out.println(name + "准备完毕！等待发令枪");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
