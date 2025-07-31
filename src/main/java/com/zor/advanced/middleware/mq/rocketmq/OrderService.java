package com.zor.advanced.middleware.mq.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderTransaction(Order order) {
        Message<Order> message = MessageBuilder.withPayload(order).build();
        rocketMQTemplate.sendMessageInTransaction(
                "order_topic", //目标topic
                message, //消息内容
                order.getId() //自定义参数会传给executeLocalTransaction
        );
    }

    public void createOrder(Order order) {
        //
    }

    public Boolean checkOrderExist(Long id) {
        // blank
        return false;
    }
}
