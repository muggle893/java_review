package org.txf.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai")
@Slf4j
public class OpenaiController {
    @Autowired
    private OpenAiChatModel openAiChatModel;

    @RequestMapping("/chat")
    public String generate(String message) {
        log.info("调用openai的api keys.");
        return openAiChatModel.call(message);
    }
}
