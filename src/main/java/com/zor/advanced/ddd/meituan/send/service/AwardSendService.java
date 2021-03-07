package com.zor.advanced.ddd.meituan.send.service;

import com.zor.advanced.ddd.meituan.dto.AwardSendResponse;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.Award;
import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;

/**
 * Created by kuqi0 on 2021/3/7
 */
public interface AwardSendService {

    AwardSendResponse sendAward(Award award, LotteryContext lotteryContext);

}
