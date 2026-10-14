package org.txf.chatbot.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.txf.chatbot.model.ChatInfo;
import org.txf.chatbot.repository.ChatInfoRepository;
import org.txf.chatbot.vo.MessageVO;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatBotController {
    @Autowired
    ChatClient chatClient;

    @Resource(name = "chatrepo")
    ChatInfoRepository chatInfoRepository;

    @Autowired
    private ChatMemory chatMemory;

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("prompt")String msg, String chatId) {
        Assert.notNull(msg, "msg must not be null");
        // 保存会话
        chatInfoRepository.save(chatId, msg);
        return chatClient
                .prompt()
                .user(msg)
                .advisors(advisorSpec -> {
                    advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId);
                })
                .stream()
                .content();
    }

    @RequestMapping("/getChatIds")
    public List<ChatInfo> getChatList() {
        return chatInfoRepository.getChatInfoList();
    }

    @RequestMapping("/getChatHistory")
    public List<MessageVO> getChatMessages(String chatId) {
        log.info("获取会话id={}的消息.", chatId);
        List<Message> messages = chatMemory.get(chatId);
        if (messages == null) {
            return List.of();
        }
        return messages.stream().map(MessageVO::new).collect(Collectors.toList());
    }

    @RequestMapping("/deleteChat")
    public boolean deleteChat(String chatId) {
        Assert.notNull(chatId, "chatId must not be null");
        chatMemory.clear(chatId);
        chatInfoRepository.deleteChatInfo(chatId);
        return true;
    }
}
