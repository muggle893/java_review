package org.txf.user.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RabbitMQConfigTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 连接rabbitmq测试队列是否创建成功
     */
    @Test
    public void test() {
        // 这里的交换机使用默认的交换机
        // routing
        rabbitTemplate.convertAndSend("", "test", "测试发送到test这个队列上");
    }
}