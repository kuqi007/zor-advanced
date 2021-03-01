package com.zor.advanced.designPattern.application;

/**
 * 美团技术团队文章代码 https://mp.weixin.qq.com/s/9gDGQhzRAL3pj35VAinZbQ
 *
 * @author zqq
 * @date 2021/3/1
 */
// 抽象策略
public abstract class RewardStrategy {
    public abstract void reward(long userId);

    public void insertRewardAnsSettlement(long userId, int reward) {
        // 更新用户信息以及结算
    }
}


class NewUserRewardStrategyA extends RewardStrategy {

    @Override
    public void reward(long userId) {
        // 具体的计算逻辑
    }
}

//
class OldUserRewardStrategyA extends RewardStrategy {

    @Override
    public void reward(long userId) {
        // 具体的计算逻辑
    }
}








