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

// 抽象的工厂
abstract class Factory<T> {
    abstract Product createProduct(Class<T> c);
}

// 具体的工厂可以生产出相应的产品
class FactoryA<T> extends Factory<T> {
    @Override
    Product createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }
}

class Test {
    public static void main(String[] args) {
        Factory<ProductA> factory = new FactoryA<>();
        Product product = factory.createProduct(ProductA.class);
        product.method();
    }
}