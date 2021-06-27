package com.zor.advanced.ratelimiter.simple;

/**
 * 漏桶算法
 *
 * @author zhuqiqi03
 * @date 2021/6/20
 */
public class LeakBucket {

    /**
     * 时间
     */
    private long time;

    /**
     * 总请求上限
     */
    private final Double total;

    /**
     * 每秒速率
     */
    private final Double rate;

    /**
     * 当前数量
     */
    private Double nowSize;

    public LeakBucket(Double total, Double rate) {
        this.total = total;
        this.rate = rate;
    }

    public boolean limit() {
        long now = System.currentTimeMillis();
        nowSize = Math.max(0, (nowSize - (now - time) / 1000D * rate));
        time = now;
        if ((nowSize + 1) < total) {
            nowSize++;
            return true;
        } else {
            return false;
        }
    }
}
