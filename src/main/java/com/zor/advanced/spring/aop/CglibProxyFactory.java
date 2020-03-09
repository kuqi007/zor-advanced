package com.zor.advanced.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zqq on 2019/7/2.
 */
public class CglibProxyFactory implements MethodInterceptor {

    //得到目标对象
    private Object target;

    //使用构造方法传递目标对象
    public CglibProxyFactory(Object target) {
        super();
        this.target = target;
    }

    public static void main(String[] args) {
        // 1.创建对象
        ProxyTest test = new ProxyTest();
        // 2.创建代理对象
        CglibProxyFactory proxy = new CglibProxyFactory(test);
        // 3.调用代理对象的增强方法,得到增强后的对象
        ProxyTest createProxy = (ProxyTest) proxy.createProxy();
        createProxy.test();
    }

    //创建代理对象
    public Object createProxy() {
        //1.创建Enhancer
        Enhancer enhancer = new Enhancer();
        //2.传递目标对象的class
        enhancer.setSuperclass(target.getClass());
        //3.设置回调操作
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    //参数一:代理对象;参数二:需要增强的方法;参数三:需要增强方法的参数;参数四:需要增强的方法的代理
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 增强前
        System.out.println("这是增强方法前......");
        Object invoke = methodProxy.invoke(target, args);
        // 增强后
        System.out.println("这是增强方法后......");
        return invoke;
    }
}

class ProxyTest {
     void test() {
        System.out.println("cglib代理测试");
    }
}
