package org.txf.demo1.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cli")
public class ChatClientController {
    private final ChatClient chatClient;

    public ChatClientController(ChatClient.Builder builder) {
        chatClient = builder
                .defaultSystem("你身份是一名编程专家")
                .build();

    }
    @RequestMapping("/chat")
    public String chat(String msg) {
        return chatClient
                // 构建一个prompt
                .prompt()
                // 用户输入的信息
                .user(msg)
                // 调用大模型
                .call()
                // 返回内容
                .content();
    }

    record Phone(String name, String size){};

    @RequestMapping("/entity")
    public Phone chat2(String msg) {
        return chatClient
                .prompt()
                .user(String.format("请帮我去查找%s的屏幕尺寸", msg))
                .call()
                .entity(Phone.class);
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8;")
    public Flux<String> chat3(String msg) {
        return chatClient
                .prompt()
                .user(msg)
                .stream()
                .content();
    }
}
