package com.zor.advanced.middleware.mq.rocketmq;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

/**
 * 订单类
 */
@Data
public class Order {


    private Long id;

    /**
     * 0: 创建订单，1: 已付款 2：已发货 3：已完成 -1：已关闭
     */
    private Integer status;

    public boolean isPaid() {
        return this.status == 0;
    }



    public Order(String payload){

    }
}
