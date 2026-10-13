package org.txf.ollamademo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ChatClient chatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient
                .builder(ollamaChatModel)
                .defaultSystem("你是一名风水大师，专门为用户解答住房的风水问题")
                .build();
    }
}
