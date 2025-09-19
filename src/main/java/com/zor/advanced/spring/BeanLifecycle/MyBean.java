package com.zor.advanced.spring.BeanLifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class MyBean implements BeanNameAware, ApplicationContextAware, BeanFactoryAware, InitializingBean, DisposableBean {

    /*
     * Aware回调
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("mybean：ApplicationContextAware接口回调：" + applicationContext.getDisplayName());
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("mybean：调用BeanNameAware, my bean name is:" + name);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("mybean：BeanFactoryAware, " + beanFactory.getBean("myBean"));
    }


    @PostConstruct
    public void postConstructor() {
        System.out.println("mybean：调用mybean的postConstruct方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("mybean：调用InitializingBean.afterPropertiesSet方法");
    }


    public void initMethod() {
        System.out.println("mybean：调用initMethod");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("mybean：调用preDestroy方法");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("mybean：调用DisposableBean.destroy方法");
    }

    public void destroyMethod() {
        System.out.println("mybean：自定义destroyMethod");
    }
}
