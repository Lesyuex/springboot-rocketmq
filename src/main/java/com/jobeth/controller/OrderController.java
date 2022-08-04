package com.jobeth.controller;

import com.jobeth.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA in 2022/8/4 19:51
 *
 * @author Jobeth
 * Description: -
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/create")
    public Order create() {
        Order order = new Order(1, "张三", "龙溪地铁站", new Date());
        for (int i = 0; i < 50; i++) {
            final int id = i;
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Order o = new Order(id, "张三" + id, "龙溪地铁站", new Date());
                this.rocketMQTemplate.convertAndSend("orderTopic", o);
            }).start();
        }
        return order;
    }
}

