package com.jobeth.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA in 2022/8/4 21:07
 *
 * @author Jobeth
 * Description: -
 */
// @Component
public class OrderConsumer {
    private DefaultMQPushConsumer consumer;


    Map<String, Object> redis = new HashMap<>();

    public OrderConsumer() throws MQClientException {
        this.consumer = new DefaultMQPushConsumer("orderConsumer");
        this.consumer.setNamesrvAddr("192.168.160.128:9876");
        consumer.subscribe("orderTopic", "*");
        this.consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list) {
                String content = new String(messageExt.getBody());
                System.out.println();

                if (redis.containsKey(content)) {
                    // 已经消费了
                    System.out.println("不允许重复消费");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                redis.put(content,true);
                // 判断是否已经消费

                // 调用服务
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        this.consumer.start();
    }
}
