package com.zor.advanced.designPattern.strategy;

import com.zor.advanced.designPattern.factory.Factory;
import com.zor.advanced.designPattern.factory.FactoryA;

/**
 * @author zqq
 * @date 2021/3/1
 */
// 定义一个策略接口
public interface Strategy {
    void strategyImplementation();
}

// 具体的策略实现（可以定义多个具体的策略实现）
class StrategyA implements Strategy {
    @Override
    public void strategyImplementation() {
        System.out.println("正在执行策略A");
    }
}

// 封装策略，屏蔽高层模块对策略、算法的直接访问，屏蔽可能存在的策略变化
class Context {
    private Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy() {
        strategy.strategyImplementation();
    }
}


class StrategyTest {
    public static void main(String[] args) {
        // 工厂方法模式帮助我们直接产生一个具体的策略对象，策略模式帮助我们保证这些策略对象可以自由地切换而不需要改动其他逻辑，从而达到解耦的目的。
        Factory factory = new FactoryA();
        Strategy strategy = factory.createStrategy(StrategyA.class);
        //
        Context context = new Context(strategy);
        context.doStrategy();
    }
}