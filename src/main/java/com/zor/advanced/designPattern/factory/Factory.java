package com.zor.advanced.designPattern.factory;

import com.zor.advanced.designPattern.strategy.Strategy;

// 抽象的工厂
public abstract class Factory<T> {

    public abstract Product createProduct(Class<T> c);

    public abstract Strategy createStrategy(Class<T> c);
}

