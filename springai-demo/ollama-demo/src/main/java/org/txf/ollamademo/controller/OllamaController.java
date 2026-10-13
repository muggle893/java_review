package org.txf.ollamademo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ollama")
public class OllamaController {
    @Autowired
    OllamaChatModel ollamaChatModel;

    @Autowired
    ChatClient chatClient;

    @RequestMapping("/chat")
    public String chat(String msg) {
        return ollamaChatModel.call(msg);
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> chat2(String msg) {
        return ollamaChatModel.stream(msg);
    }

    @RequestMapping(value = "/role", produces = "text/html;charset=utf-8")
    public String chat3(String msg) {
        String content = chatClient.prompt().user(msg).call().content();
        return content;
    }
}
