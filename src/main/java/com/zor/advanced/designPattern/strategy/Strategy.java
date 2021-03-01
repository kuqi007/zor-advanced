package com.zor.advanced.designPattern.strategy;

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
        Context context = new Context(new StrategyA());
        context.doStrategy();
    }
}