package com.zor.advanced.ddd.meituan.condition.service;

import com.zor.advanced.ddd.meituan.dto.LotteryConditionResult;

/**
 * Created by kuqi0 on 2021/3/7
 */
public interface LotteryConditionService {
    public LotteryConditionResult checkLotteryCondition(int lotteryId, Object userId);
}
