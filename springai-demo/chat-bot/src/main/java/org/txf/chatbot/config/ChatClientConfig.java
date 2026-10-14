package org.txf.chatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {


    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(50)
                .build();
    }


    /**
     * 把ChatClient对象交给spring管理
     * @param openAiChatModel
     * @return
     */
    @Bean
    public ChatClient openaiChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory) {
        return ChatClient
                .builder(openAiChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor(), MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
