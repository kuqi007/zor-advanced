package com.zor.advanced.ddd.meituan.lottery.domain.aggregate;

import com.zor.advanced.ddd.meituan.lottery.context.DrawLotteryContext;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.MtCityInfo;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.AwardPool;

import java.util.List;

/**
 * Created by kuqi0 on 2021/3/7
 */
public class DrawLottery {
    // 抽奖id
    private int LotteryId;
    // 奖池列表
    private List<AwardPool> awardPools;

    // set和getter
    public void setLotteryId(int lotteryId) {
        if (lotteryId < 0) {
            throw new IllegalArgumentException("非法的抽奖id");
        }
        LotteryId = lotteryId;
    }

    public int getLotteryId() {
        return LotteryId;
    }

    // 根据抽奖入参context选择奖池
    public AwardPool chooseAwardPool(DrawLotteryContext context) {
        if (context.getMtCityInfo() != null) {
            return chooseAwardPoolByCityInfo(awardPools, context.getMtCityInfo());
        } else {
            return chooseAwardPoolByScore(awardPools, context.getGameScore());
        }

    }

    private AwardPool chooseAwardPoolByCityInfo(List<AwardPool> awardPools, MtCityInfo cityInfo) {
        for (AwardPool awardPool : awardPools) {
            if (awardPool.matchedCity(cityInfo.getCityId())) {
                return awardPool;
            }
        }
        return null;
    }

    private AwardPool chooseAwardPoolByScore(List<AwardPool> awardPools, int gameScore) {
        //.....
        // 具体逻辑
        return null;
    }
}
