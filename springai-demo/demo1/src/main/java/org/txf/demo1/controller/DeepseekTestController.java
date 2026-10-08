package org.txf.demo1.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deepseek")
public class DeepseekTestController {
    @Autowired
    OpenAiChatModel openAiChatModel;
    @RequestMapping("/chat")
    public String chat(String message) {
        return openAiChatModel.call(message);
    }
}
