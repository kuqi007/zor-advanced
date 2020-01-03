package com.zor.advanced.designPattern.singleton;

/**
 * 静态内部类懒汉式
 * 1.当任何一个线程第一次调用getInstance()时，都会使SingletonHolder被加载和被初始化，
 * 此时静态初始化器将执行Singleton的初始化操作。(被调用时才进行初始化！)
 * 2.初始化静态数据时，Java提供了的线程安全性保证。(所以不需要任何的同步)
 */
public class StaticInnerClassLazySingleton {
    private StaticInnerClassLazySingleton() {

    }

    private static StaticInnerClassLazySingleton getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static final StaticInnerClassLazySingleton instance = new StaticInnerClassLazySingleton();
    }
}
