package com.zor.advanced.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zqq on 2019/7/25.
 */
public class JDKProxyFactory implements InvocationHandler {

    private Object target;

    public JDKProxyFactory(Object target) {
        super();
        this.target = target;
    }

    // 创建代理对象
    public Object createProxy() {
        // 1.得到目标对象的类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 2.得到目标对象的实现接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 3.第三个参数需要一个实现invocationHandler接口的对象

        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    // 第一个参数:代理对象.一般不使用;第二个参数:需要增强的方法;第三个参数:方法中的参数
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 增强前
        System.out.println("这是增强方法前......");
        Object invoke = method.invoke(target, args);
        // 增强后
        System.out.println("这是增强方法后......");
        return invoke;
    }

    public static void main(String[] args) {
        Subject subject = (Subject) new JDKProxyFactory(new RealSubject()).createProxy();

        subject.doSomething();

    }
}

interface Subject {

    void doSomething();
}

/**
 * RealSubject
 * 真实主题类
 *
 * @author
 * @create 2018-03-29 14:21
 **/
class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}
