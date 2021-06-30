package com.zor.basic.highconcurrency.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zqq
 * @date 2021/3/1
 */
public class CompletableFutureTest {

    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(CompletableFutureTest::step1)
                .thenCombine(CompletableFuture.supplyAsync(CompletableFutureTest::step2), (i, j) -> i * j)
                .thenCombine(CompletableFuture.supplyAsync(CompletableFutureTest::step3), (i, j) -> i * j);
        Integer res = future1.get();
        System.out.println(res);
        long end = System.currentTimeMillis();
        System.out.println("Combine整体耗时：" + (end - start) + " ms");
    }


    @Test
    public void testCompose() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(CompletableFutureTest::step1)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer + step2()))
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer + step3()));
        Integer res = future1.get();
        System.out.println(res);
        long end = System.currentTimeMillis();
        System.out.println("Compose整体耗时：" + (end - start) + " ms");
    }

    private static int step1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("step1执行完毕");
        return 1;
    }

    private static int step2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("step2执行完毕");
        return 2;
    }

    private static int step3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("step3执行完毕");
        return 3;
    }
}
