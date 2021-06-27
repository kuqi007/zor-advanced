package com.zor.advanced.ratelimiter.advanced;

/**
 * Created by kuqi0 on 2021/6/27
 */
public interface MyRateLimiter {

    /**
     * 尝试获取流量
     *
     * @return true 表示当前流量可以放行，否则表示拒绝
     */
    boolean tryAcquire();

}
