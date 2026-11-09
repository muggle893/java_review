package org.txf.user.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueListener {

    @RabbitListener(queues = "test")
    public void listen(Message message) {
        log.info("接收到队列消息:{}", message);
    }
}
