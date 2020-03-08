package com.zor.advanced.spring.BeanLifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User implements InitializingBean, DisposableBean,
        BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String name;

    public User() {
        System.out.println("调用Bean的函数(constructor)");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("调用Bean的函数(setName/setAttribute)");
        this.name = name;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("调用Bean的函数(postConstruct)");
    }

    //MainConfig中@Bean 的initMethod
    public void initMethod() {
        System.out.println("调用Bean的函数(initMethod)");
    }

    //InitializingBean接口的方法afterPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用Bean的函数(afterPropertiesSet)");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("调用Bean的函数(preDestroy)");
    }

    //DisposableBean接口的方法destroy
    @Override
    public void destroy() throws Exception {
        System.out.println("调用Bean的函数(destroy)");
    }

    //MainConfig中@Bean 的destroyMethod
    public void destroyMethod() {
        System.out.println("调用Bean的函数(destroyMethod)");
    }

    //**************************Aware相关***********************

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware：" + s);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware");
    }
}
