package com.zor.advanced.spring.levelcache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootTest
public class CacheLevelTest {

    @Autowired
    private ConfigurableApplicationContext context;
    
    @Test
    public void traceNormalBeanCache() {
        DefaultSingletonBeanRegistry registry =
            (DefaultSingletonBeanRegistry) context.getBeanFactory();
        
        System.out.println("Before getting normalBean");
        printCacheStatus(registry, "normalBean");
        
        // 获取Bean触发创建
        context.getBean("normalBean");
        
        System.out.println("After getting normalBean");
        printCacheStatus(registry, "normalBean");
    }
    
    void printCacheStatus(DefaultSingletonBeanRegistry reg, String name) {
        System.out.println("L1 Cache: " + reg.getSingleton(name));
        System.out.println("L2 Cache: " + ((Map<?, ?>)reg.getSingletonMutex()).get("earlySingletonObjects"));
        System.out.println("L3 Factory: " + ((Map<?, ?>)reg.getSingletonMutex()).get("singletonFactories"));
    }
}