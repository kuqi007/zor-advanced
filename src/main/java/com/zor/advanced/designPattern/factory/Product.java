package com.zor.advanced.designPattern.factory;

/**
 * @author zqq
 * @date 2021/3/1
 */
// 抽象的产品
public abstract class Product {
    public abstract void method();
}

// 定义一个具体的产品（可以定义多个具体的产品）
class ProductA extends Product {
    @Override
    public void method() {
        System.out.println("我是产品A，do sth");
        // 具体的执行逻辑
    }
}


class Test {
    public static void main(String[] args) {
        Factory factory = new FactoryA();
        Product product = factory.createProduct(ProductA.class);
        product.method();
    }
}