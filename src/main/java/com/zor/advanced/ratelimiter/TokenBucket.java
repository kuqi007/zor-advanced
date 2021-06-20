package com.zor.advanced.ratelimiter;

/**
 * 令牌桶
 * Created by kuqi0 on 2021/6/20
 */
public class TokenBucket {

    /**
     * 时间
     */
    private long time;

    /**
     * 总量
     */
    private final Double total;

    /**
     * token 放入速度
     */
    private final Double rate;

    /**
     * 当前总量
     */
    private Double nowSize;

    public TokenBucket(Double total, Double rate) {
        this.total = total;
        this.nowSize = total;
        this.rate = rate;
    }

    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket(100.0, 2.0);
        for (int i = 1; i <= 120; i++) {
            boolean result = tokenBucket.limit();
            System.out.println("第" + i + "次请求结果：" + result);
        }
    }

    public boolean limit() {
        long now = System.currentTimeMillis();
        nowSize = Math.min(total, nowSize + (now - time) * rate);
        time = now;
        if (nowSize < 1) {
            return false;
        } else {
            nowSize -= 1;
            return true;
        }

    }
}
