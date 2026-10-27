package org.txf.client;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chat")
@RestController
public class ChatConteroller {
    ChatClient chatClient;

    public ChatConteroller(DashScopeChatModel dashScopeChatModel, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = ChatClient.builder(dashScopeChatModel)
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    @RequestMapping("/t1")
    public String chat(String msg) {
        return chatClient.prompt()
                .user(msg)
                .call()
                .content();
    }
}
