package com.jobeth.service;

import com.jobeth.entity.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA in 2022/8/4 20:04
 *
 * @author Jobeth
 * Description: -
 */
@Service
@RocketMQMessageListener(consumerGroup = "orderConsumer", topic = "orderTopic", consumeMode = ConsumeMode.ORDERLY)
public class OrderConsumerListner implements RocketMQListener<Order> {
    Map<Integer, Object> redis = new HashMap<>();
    int i = 0;
    Lock lock = new ReentrantLock();

    @Override
    public void onMessage(Order order) {
        if (redis.containsKey(order.getId())) {
            // 已经消费了
            System.out.println("不允许重复消费");
            return;
        }
        try {
            // 调用服务
            if (order.getId() == 10) {
                TimeUnit.SECONDS.sleep(40);
            }
            System.out.println("开始消费=> " + order.getId()  +  "    第" + getI());
        } catch (Exception e) {
            System.err.println("调用服务错误");
        } finally {
            redis.put(order.getId(), true);
        }
    }

    public  synchronized int getI(){
        return ++this.i;
    }
}
