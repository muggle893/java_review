package org.txf.qianfan.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qianfan")
public class ChatController {

    @Autowired
    OpenAiChatModel openAiChatModel;


    @RequestMapping("/chat")
    public String chat(String msg) {
        return openAiChatModel.call(msg);
    }
}
