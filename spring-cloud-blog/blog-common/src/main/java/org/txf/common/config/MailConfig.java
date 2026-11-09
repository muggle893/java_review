package org.txf.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.txf.common.utils.Mail;

@Configuration
@ConditionalOnProperty(prefix = "spring.mail", value = {"username"})
public class MailConfig {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public Mail getMail() {
        // 创建新的对象直接返回
        return new Mail(mailSender, mailProperties);
    }
}
