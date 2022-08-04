package com.jobeth.service;

import com.jobeth.entity.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA in 2022/8/4 20:04
 *
 * @author Jobeth
 * Description: -
 */
@Service
@RocketMQMessageListener(consumerGroup = "orderConsumer", topic = "orderTopic")
public class OrderConsumerListner implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        System.out.println("order => " + order);
    }
}
