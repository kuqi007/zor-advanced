package com.zor.advanced.ratelimiter.advanced;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口计数器TreeMap版，因为TreeMap没有线程安全版本，所以使用synchronized保证线程安全
 * Created by kuqi0 on 2021/6/27
 */
public class SlidingWindowRateLimiter implements MyRateLimiter {

    /**
     * 每分钟限制请求数
     */
    private final long permitsPerMinute;

    /**
     * 时间窗口间隔秒数
     */
    private final int seconds;

    /**
     * 计数器, key为当前窗口的开始时间值毫秒，value为当前窗口的计数
     */
    private final TreeMap<Long, Integer> counters;

    public SlidingWindowRateLimiter(long permitsPerMinute, int seconds) {
        this.permitsPerMinute = permitsPerMinute;
        this.seconds = seconds;
        this.counters = new TreeMap<>();
    }

    @Override
    public synchronized boolean tryAcquire() {
        // 获取当前时间的所在的子窗口值
        long currentTimeMillis = System.currentTimeMillis();
        // 获取当前窗口的请求总量
        int currentWindowCount = getCurrentWindowCount(currentTimeMillis);
        System.out.println("当前窗口计数：" + currentWindowCount);
        if (currentWindowCount >= permitsPerMinute) {
            return false;
        }
        // 计数器 + 1
        saveCurrentTime(currentTimeMillis);
        return true;
    }


    /**
     * 计时器 +1
     */
    private void saveCurrentTime(long currentTimeMillis) {
        counters.merge(currentTimeMillis, 1, Integer::sum);
    }

    /**
     * 获取当前窗口中的所有请求数（并删除所有无效的子窗口计数器）
     *
     * @param currentTimesMills 当前子窗口时间
     * @return 当前窗口中的计数
     */
    private int getCurrentWindowCount(long currentTimesMills) {
        // 计算出窗口的开始位置时间
        long startTime = currentTimesMills - (seconds * 1000L);
        int result = 0;
        // 遍历当前存储的计算器，删除无效的子窗口计数器，并累加当前窗口中的所有计数器之和
        Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            Long time = entry.getKey();
            if (time < startTime) {
                iterator.remove();
            } else {
                result += entry.getValue();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 将时间分片，seconds是时间窗口大小，max代表seconds时间最大访问上限
        MyRateLimiter myRateLimiter = new SlidingWindowRateLimiter(10, 2);

        int requestCnt = 60;
        AtomicInteger successCnt = new AtomicInteger();
        long start = System.currentTimeMillis();
        for (int i = 0; i < requestCnt; i++) {
            boolean result = myRateLimiter.tryAcquire();
            if (result) {
                successCnt.incrementAndGet();
            }
            String resultStr = result ? "请求成功" : "请求失败";
            long now = System.currentTimeMillis();
            System.out.println("第" + (i + 1) + "次请求结果：" + resultStr + "，耗时：" + (now - start));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("全部请求结束，成功率：" + (successCnt.get() * 100 / requestCnt) + "%");
    }
}
