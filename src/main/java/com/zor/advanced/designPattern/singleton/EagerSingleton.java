package com.zor.advanced.designPattern.singleton;

/**
 * 简单饿汉式
 * 缺点： 一上来就创建对象了，如果该实例从始至终都没被使用过，则会造成内存浪费；
 * 但是是线程安全的，因为初始化静态数据时，Java提供了的线程安全性保证。(所以不需要任何的同步)
 */
public class EagerSingleton {
    // 2.在类的内部创建自行实例
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    // 1.将构造函数私有化，不可以通过new的方式来创建对象
    private EagerSingleton() {
    }

    // 3.提供获取唯一实例的方法
    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }

}
