package com.zor.advanced.designPattern.singleton;

/**
 * 枚举方式实现：
 * 好处：
 *      1.简单，直接写就行了
 *      2.防止多次实例化，即使是在面对复杂的序列化或者反射攻击的时候(安全)！
 */
public enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
