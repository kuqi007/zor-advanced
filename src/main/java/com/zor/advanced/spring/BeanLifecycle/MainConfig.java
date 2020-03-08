package com.zor.advanced.spring.BeanLifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainConfig {
    public static void main(String[] args) {
        SpringApplication.run(MainConfig.class, args);
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod", name = "zqqBean")
    public User user() {
        return new User();
    }
}
