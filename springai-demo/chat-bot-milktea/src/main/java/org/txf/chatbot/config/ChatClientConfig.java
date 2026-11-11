package org.txf.chatbot.config;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Autowired
    private AdvisorFactory advisorFactory;

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(50)
                .build();
    }


    /**
     * 把ChatClient对象交给spring管理
     * @param dashScopeChatModel
     * @return
     */
    @Bean
    public ChatClient chatClient(DashScopeChatModel dashScopeChatModel, ChatMemory chatMemory) {
        String systemPrompt = """
                            你是⼀名专业的奶茶店智能客服, 代表栖叶茶事·青禾里店为客户提供此店的奶茶咨询服务.
                            你的职责是准确、礼貌、⾼效地解答客⼾关于栖叶茶事·青禾里店的各类问题
                            """;
        return ChatClient
                .builder(dashScopeChatModel)
                .defaultSystem(systemPrompt)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultAdvisors(advisorFactory.createQuestionAnswerAdvisor())
                .defaultAdvisors(advisorFactory.createRetrievalRerankAdvisor())
                .build();
    }
}
