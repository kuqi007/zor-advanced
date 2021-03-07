package com.zor.advanced.ddd.meituan.counter.service;

import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;

/**
 * Created by kuqi0 on 2021/3/7
 */
public interface AwardCounterService {

    void incrTryCount(LotteryContext lotteryContext);

}
