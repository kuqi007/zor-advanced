package com.zor.advanced.middleware.mq.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQListener;

public class OrderConsumer implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        System.out.println("收到订单消息：" + order);
        //  处理业务逻辑
    }
}
