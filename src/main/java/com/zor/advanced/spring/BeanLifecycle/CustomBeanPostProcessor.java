package com.zor.advanced.spring.BeanLifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 初始化的前置方法和后置方法是为所有的bean服务的，而非为某一个Bean服务，所以不能写在某个具体的bean中，否则不会执行
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class || bean.getClass() == MyBean.class) {
            System.out.println("调用postProcessBeforeInitialization...，beanName：" + beanName);
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class || bean.getClass() == MyBean.class) {
            System.out.println("调用postProcessAfterInitialization...beanName：" + beanName);
        }
        return bean;
    }
}
