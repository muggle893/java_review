package org.txf.demo1.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient ChatClientConfig(ChatClient.Builder builder) {
        return builder.defaultSystem("你是问小白，一个智能助手.")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
