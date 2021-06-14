package com.zor.basic.highconcurrency.tools.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * jdk8 CyclicBarrier官方示例{@link CyclicBarrier}
 * <p>
 * 在这里，每个工作线程处理矩阵的一行，然后在屏障处等待，直到处理完所有行。
 * 处理完所有行后，将执行提供的Runnable屏障操作并合并行。
 * 如果合并确定已找到解决方案，则done()将返回true并且每个 worker 将终止。
 */
class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        public void run() {
            while (!done()) {
                processRow(myRow);

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    return;
                }
            }
        }


    }

    public Solver(float[][] matrix) throws InterruptedException {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction =
                new Runnable() {
                    public void run() {
                        mergeRows();
                    }
                };
        barrier = new CyclicBarrier(N, barrierAction);

        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }

        // wait until done
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private void processRow(int myRow) {
        // 真正逻辑
    }

    private boolean done() {
        // 真正逻辑
        return true;
    }

    private void mergeRows() {
        // 真正逻辑
    }


}