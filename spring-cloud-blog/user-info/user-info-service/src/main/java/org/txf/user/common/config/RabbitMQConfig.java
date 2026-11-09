package org.txf.user.common.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.txf.user.constant.Constants;

@Configuration
public class RabbitMQConfig {
    /**
     * 声明用户队列
     * @return
     */
    @Bean("userQueue")
    public Queue userQueue() {
        return QueueBuilder.durable(Constants.USER_QUEUE_NAME).build();
    }

    /**
     * 声明用户交换机
     * @return
     */
    @Bean("userExchange")
    public FanoutExchange userExchange() {
        return ExchangeBuilder
                .fanoutExchange(Constants.USER_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    /**
     * 声明用户队列和用户交换机的绑定关系
     * @return
     */
    @Bean
    public Binding userBinding(@Qualifier("userQueue")Queue queue, @Qualifier("userExchange")FanoutExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange);
    }
}
