package org.txf.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ds")
@Slf4j
public class DeepseekChatController {
    private final ChatClient chatClient;

    public DeepseekChatController(ChatClient.Builder builder) {
        // 可以设置系统默认角色
        this.chatClient = builder.defaultSystem("你是问小白，一个智能助手.").build();
    }
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @RequestMapping("/chat")
    public String testchat(String prompt) {
        return openAiChatModel.call(prompt);
    }

    @RequestMapping("/call")
    public String testClient(String message) {
        return this.chatClient.prompt()
                // 用户输入信息
                .user(message)
                // 请求大模型
                .call()
                .content();
    }
}
