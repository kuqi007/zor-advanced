package com.zor.advanced.ddd.meituan.lottery.service.impl;

import com.zor.advanced.ddd.meituan.lottery.context.DrawLotteryContext;
import com.zor.advanced.ddd.meituan.lottery.domain.aggregate.DrawLottery;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.Award;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.AwardPool;
import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;
import com.zor.advanced.ddd.meituan.lottery.facade.UserCityInfoFacade;
import com.zor.advanced.ddd.meituan.counter.service.AwardCounterService;
import com.zor.advanced.ddd.meituan.dto.AwardSendResponse;
import com.zor.advanced.ddd.meituan.send.service.AwardSendService;
import com.zor.advanced.ddd.meituan.lottery.repo.repository.DrawLotteryRepository;
import com.zor.advanced.ddd.meituan.dto.IssueResponse;
import com.zor.advanced.ddd.meituan.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    private DrawLotteryRepository drawLotteryRepo;
    @Autowired
    private UserCityInfoFacade userCityInfoFacade;
    @Autowired
    private AwardSendService awardSendService;
    @Autowired
    private AwardCounterService awardCounterService;

    @Override
    public IssueResponse issueLottery(LotteryContext lotteryContext) {
        // 获取抽奖配置聚合根
        DrawLottery drawLottery = drawLotteryRepo.getDrawLotteryById(lotteryContext.getLotteryId());
        // 增加抽奖次数信息
        awardCounterService.incrTryCount(lotteryContext);
        // 选中奖池
        AwardPool awardPool = drawLottery.chooseAwardPool(buildDrawLotteryContext(drawLottery, lotteryContext));
        // 选中奖品
        Award award = awardPool.randomGetAward();
        // 发出奖品实体
        return buildIssueResponse(awardSendService.sendAward(award, lotteryContext));
    }

    private DrawLotteryContext buildDrawLotteryContext(DrawLottery drawLottery, LotteryContext lotteryContext) {
        // ...具体逻辑
        return new DrawLotteryContext();
    }

    private IssueResponse buildIssueResponse(AwardSendResponse awardSendResponse) {
        return null;
    }

}
