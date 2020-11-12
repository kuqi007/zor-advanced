package com.zor.advanced.spring.ioc.demo.service.impl;

import com.zor.advanced.spring.ioc.demo.annotation.IocService;
import com.zor.advanced.spring.ioc.demo.service.IOrderService;

@IocService
public class OrderService implements IOrderService {

    public String findOrder(String username) {
        return "用户" + username + "的订单编号是:1001";
    }
}