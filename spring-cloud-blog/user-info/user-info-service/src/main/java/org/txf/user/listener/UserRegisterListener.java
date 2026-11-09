package org.txf.user.listener;

import com.rabbitmq.client.Channel;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.txf.common.utils.JSONUtils;
import org.txf.common.utils.Mail;
import org.txf.user.common.pojo.dataobject.UserInfo;
import org.txf.user.constant.Constants;

import java.io.IOException;

@Component
@Slf4j
public class UserRegisterListener {
    @Autowired
    Mail mail;

    /**
     * 用法1,@RabbitListener其他用法
     */
//    @RabbitListener(queues = Constants.USER_QUEUE_NAME)
//    public void listenUser(Message message, Channel channel) throws IOException {
//        long deliveryTag = -1;
//        try {
//             deliveryTag = message.getMessageProperties().getDeliveryTag();
//            // 接收消息
//            String body = new String(message.getBody());
//            log.info("用户注册，从用户队列中取出消息，{}", body);
//
//            // 发送邮件给用户
//
//            // 手动确认消息接收成功
//            channel.basicAck(deliveryTag, true);
//        } catch (IOException e) {
//            // 确认消息失败则重新确认
//            channel.basicNack(deliveryTag, true, true);
//        }
//    }


    /**
     * 用法2
     */
    @RabbitListener(
            bindings = @QueueBinding(
            value = @Queue(value = Constants.USER_QUEUE_NAME, durable = "true"),
            exchange = @Exchange(value = Constants.USER_EXCHANGE_NAME, type = "fanout")
        )
    )
    public void listenUser(Message message, Channel channel) throws IOException {
        long deliveryTag = -1;
        try {
             deliveryTag = message.getMessageProperties().getDeliveryTag();
            // 接收消息
            String body = new String(message.getBody());
            log.info("用户注册，从用户队列中取出消息，{}", body);

            // 发送邮件给用户
            // 把body的字符串转为对象然后拿到对象里面的username
            UserInfo userInfo = JSONUtils.toObject(body, UserInfo.class);
            String content = new StringBuilder()
            .append("尊敬的").append(userInfo.getUserName()).append(", 您好! <br/>")
            .append("感谢您注册成为我们博客社区的⼀员! 我们很⾼兴您决定加⼊我们的⼤家庭. <br/>")
                    .append("您的注册信息如下: </br/>")
                    .append("⽤⼾名:").append(userInfo.getUserName()).append("<br/>")
                    .append("为了确保您的账⼾安全，请妥善保管您的登录信息. 如果使⽤过程中, 遇到任何 问题, 欢迎联系我们的⽀持团队.  <br/>")
            .append("再次感谢您的加⼊，我们期待看到您的精彩内容！<br/>")
            .append("最好的祝愿<br/>")
            .append("晓峰博客").toString();

            mail.send(userInfo.getEmail(), "欢迎来到晓峰博客.", content);

            // 手动确认消息接收成功
            channel.basicAck(deliveryTag, true);
        } catch (IOException e) {
            // 确认消息失败则重新确认
            channel.basicNack(deliveryTag, true, true);
        } catch (MessagingException e) {
            log.warn("邮件发送异常!");
        }
    }
}
