package com.zor.advanced.designPattern.application;

/**
 * 美团技术团队文章代码 https://mp.weixin.qq.com/s/9gDGQhzRAL3pj35VAinZbQ
 *
 * @author zqq
 * @date 2021/3/1
 */
// 抽象策略
public abstract class RewardStrategy {
    public abstract int reward(long userId);

    public void insertRewardAnsSettlement(long userId, int reward) {
        // 更新用户信息以及结算
    }
}

// 新用户返奖具体策略A
class NewUserRewardStrategyA extends RewardStrategy {

    @Override
    public int reward(long userId) {
        // 具体的计算逻辑
        return 0;
    }
}

// 老用户返奖具体策略A
class OldUserRewardStrategyA extends RewardStrategy {

    @Override
    public int reward(long userId) {
        // 具体的计算逻辑
        return 0;
    }
}

// 抽象工厂
abstract class StrategyFactory<T> {
    abstract RewardStrategy createStrategy(Class<T> c);
}

// 具体工厂创建具体的策略
class FactoryRewardStrategyFactory extends StrategyFactory {

    @Override
    RewardStrategy createStrategy(Class c) {
        RewardStrategy product = null;
        try {
            product = (RewardStrategy) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }
}

class Test{
    public static void main(String[] args) {
        FactoryRewardStrategyFactory factoryRewardStrategyFactory=new FactoryRewardStrategyFactory();
        RewardStrategy strategy = factoryRewardStrategyFactory.createStrategy(OldUserRewardStrategyA.class);
        strategy.reward(1);
    }
}








