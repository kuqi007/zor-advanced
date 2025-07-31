package com.zor.advanced.middleware.mq.rocketmq;

import com.alibaba.fastjson2.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 假设这是一个订单系统，我们来模拟一个事务消息的场景
 */
@RocketMQTransactionListener
public class OrderTransactionListener implements RocketMQLocalTransactionListener {

    private final OrderService orderService;

    public OrderTransactionListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            Order order = parseOrder(new String((byte[]) msg.getPayload()));
            orderService.createOrder(order);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 回查本地事务状态（防止生产者宕机导致消息状态位置）
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        Order order = parseOrder(new String((byte[]) msg.getPayload()));
        Boolean exists = orderService.checkOrderExist(order.getId());
        return exists ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
    }


    public Order parseOrder(String payload) {
        try {
            return JSON.parseObject(payload, Order.class);
        } catch (Exception e) {
            return null;
        }
    }
}



