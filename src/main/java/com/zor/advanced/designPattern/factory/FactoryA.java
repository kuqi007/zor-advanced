package com.zor.advanced.designPattern.factory;

import com.zor.advanced.designPattern.strategy.Strategy;

// 具体的工厂可以生产出相应的产品
public class FactoryA extends Factory{
    @Override
    public Product createProduct(Class c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Strategy createStrategy(Class c) {
        Strategy strategy = null;
        try {
            strategy = (Strategy) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return strategy;
    }
}