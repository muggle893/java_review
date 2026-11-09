package org.txf.user.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class MailTest {
    @Autowired
    JavaMailSender mailSender;

    @Test
    public void send() throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom("1786772884@qq.com", "晓峰博客系统.");
        helper.addTo("txf893@126.com");
        helper.setSubject("这是一封邮箱测试邮件");
        helper.setText("<h1>你好.</h1>", true);
        mailSender.send(message);
    }
}
