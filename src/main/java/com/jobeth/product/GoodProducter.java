package com.jobeth.product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Created with IntelliJ IDEA in 2022/8/4 19:33
 *
 * @author Jobeth
 * Description: -
 */
public class GoodProducter {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("good-group");
        producer.setNamesrvAddr("192.168.160.128:9876");
        producer.start();
        Message message = new Message("myTopic", "myTag", "生产了汉堡1".getBytes());
        SendResult send = producer.send(message, 1000);
        System.out.println(send);
        producer.shutdown();
    }
}
