package com.zor.advanced.spring.ioc.demo.service.impl;

import com.zor.advanced.spring.ioc.demo.annotation.IocResource;
import com.zor.advanced.spring.ioc.demo.annotation.IocService;
import com.zor.advanced.spring.ioc.demo.service.IOrderService;
import com.zor.advanced.spring.ioc.demo.service.IUserService;

@IocService/*(name = "userbiz")*/
public class UserService implements IUserService {

    /*比较脆弱啊 这块的属性名称一定要用实现类来命名 且 按照第一个字母要小写的原则 否则很报错的*/
    @IocResource
    private IOrderService orderService;

    public String findOrder(String username) {
        return orderService.findOrder(username);
    }
}