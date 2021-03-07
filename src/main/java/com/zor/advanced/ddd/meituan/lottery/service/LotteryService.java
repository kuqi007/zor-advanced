package com.zor.advanced.ddd.meituan.lottery.service;

import com.zor.advanced.ddd.meituan.dto.IssueResponse;
import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;

/**
 * Created by kuqi0 on 2021/3/7
 */
public interface LotteryService {
    IssueResponse issueLottery(LotteryContext lotteryContext);
}
