package com.zor.advanced.spring.ioc.demo;

import com.zor.advanced.spring.ioc.demo.context.SpringContext;
import com.zor.advanced.spring.ioc.demo.service.IUserService;
import com.zor.advanced.spring.ioc.demo.service.impl.UserService;

/**
 * @author zqq
 * @date 2020/11/12
 */
public class SpringTest {

    public static void main(String[] args) throws Exception {
        String path = "com.zor.advanced.spring.ioc.demo.service.impl";
        SpringContext springContext = new SpringContext(path);
        IUserService iUserService = (IUserService) springContext.getBean("userbiz");
        System.out.println(iUserService.findOrder("aaa"));


    }
}
